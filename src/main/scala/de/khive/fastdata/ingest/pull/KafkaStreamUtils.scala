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

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Sink
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

import scala.concurrent.Future

/**
  * Kafka Stream Utils
  *
  * Created by ceth on 03.11.16.
  */
object KafkaStreamUtils {

  def createIngestStream(config: IngestConfig)(implicit system: ActorSystem): Sink[ProducerRecord[String, Array[Byte]], Future[Done]] = {
    val producerSettings = ProducerSettings[String, Array[Byte]](system, new StringSerializer, new ByteArraySerializer)
      .withBootstrapServers(config.forwardKafkaBootstrapServer)
      .withProperty("client.id", config.forwardKafkaClientId)

    Producer.plainSink(producerSettings)
  }

}