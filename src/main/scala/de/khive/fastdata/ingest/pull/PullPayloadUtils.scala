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

import java.nio.charset.StandardCharsets

import org.slf4j.LoggerFactory

import scala.io.Source

/**
  * Utils for pulling remote content and forward it
  * to stream subscribers.
  *
  * Created by ceth on 03.11.16.
  */
object PullPayloadUtils {

  val log = LoggerFactory.getLogger(getClass)

  /**
    * Pull content from <code>url</code> and forward it using the <code>forwardFunction</code>.
    *
    * @param url
    */
  def pullPayload(url: String): Array[Byte] = {
    log.debug(s"Pull payload from ${url}...")
    Source.fromURL(url).mkString.getBytes(StandardCharsets.UTF_8)
  }
}