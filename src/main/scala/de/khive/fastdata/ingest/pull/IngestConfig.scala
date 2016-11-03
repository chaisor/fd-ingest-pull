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

import com.typesafe.config.ConfigFactory

/**
  * Created by ceth on 03.11.16.
  */
object IngestConfig {

  def fromArgs(args: Array[String]): IngestConfig = {

  }

  def fromApplicationConf(): IngestConfig = {
    val conf = ConfigFactory.load()

    IngestConfig(
      conf.getString("ingest.pull.url"),
      conf.getInt("ingest.pull.duration"),
      conf.getString("ingest.forward.kafka.clientId"),
      conf.getString("ingest.forward.kafka.bootstrapServer"),
      conf.getString("ingest.forward.kafka.zookeeperConnect"),
      conf.getString("ingest.forward.kafka.topic")
    )
  }

}

/**
  * Current Config Case Class
  *
  * @param pullUrl
  * @param pullDuration
  * @param forwardKafkaClientId
  * @param forwardKafkaBootstrapServer
  * @param forwardKafkaZookeeperConnect
  * @param forwardKafkaTopic
  */
sealed case class IngestConfig(
              pullUrl: String,
              pullDuration: Int,
              forwardKafkaClientId: String,
              forwardKafkaBootstrapServer: String,
              forwardKafkaZookeeperConnect: String,
              forwardKafkaTopic: String)