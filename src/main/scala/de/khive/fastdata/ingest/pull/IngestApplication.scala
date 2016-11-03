/*
 * This file is part of fd-ingest-pull (further: this software).
 *
 * Copyright (C) 2016  Bastian Kraus
 *
 * This software is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version)
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.khive.fastdata.ingest.pull

import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import akka.stream.{ActorMaterializer, OverflowStrategy}
import org.apache.kafka.clients.producer.ProducerRecord

import scala.concurrent.duration._
/**
  * Ingest Application<br/>
  * <br/>
  * Entry point for pull ingest: contains main(..) method to run.
  *
  * Created by ceth on 03.11.16
  */
object IngestApplication extends App {

  val config = getConfig()
  implicit val system = ActorSystem("fd-ingest-pull")
  implicit val materializer = ActorMaterializer()

  val pullSource = Source.actorRef[Array[Byte]](IngestConfig.SOURCE_ACTOR_BUFFER_SIZE, OverflowStrategy.fail)
  val sink = KafkaStreamUtils.createIngestStream(config)(system)
  val pullSourceRef = pullSource.map(payload => new ProducerRecord[String, Array[Byte]](config.forwardKafkaTopic, "", payload)).to(sink).run()

  import system.dispatcher
  system.scheduler.schedule(0 seconds, config.pullDuration.toLong seconds, pullSourceRef, PullPayloadUtils.pullPayload(config.pullUrl))

  private def getConfig(): IngestConfig = {
    args match {
      case a:Array[String] if args.length > 0 => IngestConfig.fromArgs(args)
      case _ => IngestConfig.fromApplicationConf()
    }
  }

}

object IngestApplicationArgs extends Enumeration {
  type IngestApplicationArgs = Value

  val Url                     = Value(0)
  val PullDuration            = Value(1)
  val KafkaClientId           = Value(2)
  val KafkaBootstrapServer    = Value(3)
  val KafkaZookeeperConnect   = Value(4)
  val KafkaTopic              = Value(5)
}