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

import akka.actor.Actor
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

/**
  * Kafka Raw Payload ingestion [[akka.actor.Actor]]
  *
  * Created by ceth on 03.11.16.
  */
class KafkaIngestActor(config: IngestConfig) extends Actor {

  val producerSettings = ProducerSettings(IngestApplication.system, new ByteArraySerializer, new StringSerializer)
  // TODO: implement producer as reactive sink
  //val producer = Producer.

  override def receive: Receive = {
    case IngestPayload(payload) => ingest(payload)
    case _ => throw new IllegalArgumentException(s"KafkaIngestActor accepts commands: ${IngestPayload.getClass.getName}")
  }

  def ingest(payload: Array[Byte]): Unit = {

  }

}

/**
  * Base Actor Message Commands for [[KafkaIngestActor]]
  */
sealed trait KafkaIngestActorCommand

/**
  * Actor command for ingest payload onto
  * @param payload
  */
case class IngestPayload(payload: Array[Byte])  extends KafkaIngestActorCommand