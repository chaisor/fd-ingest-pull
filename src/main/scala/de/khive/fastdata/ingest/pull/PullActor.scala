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

import akka.actor.{Actor, Props}

import scala.io.Source

/**
  * Actor for pulling remote content
  * Created by ceth on 03.11.16.
  */
class PullActor extends Actor {

  override def receive: Receive = {
    case DoPullRequest(url, forwardFunction) => pull(url, forwardFunction)
    case _ => throw new IllegalArgumentException(s"PullActor accepts commands: ${DoPullRequest.getClass.getName}")
  }

  /**
    * Pull content from <code>url</code> and forward it using the <code>forwardFunction</code>.
    *
    * @param url
    * @param forwardFunction
    */
  protected def pull(url: String, forwardFunction: (String) => Unit): Unit = forwardFunction(Source.fromURL(url).mkString)

}

/**
  * Base Command Trait for PullActor Commands
  */
sealed trait PullActorCommand

/**
  * Send to pull actor with <code>url</code> to trigger a pull request
  * and fetch the payload
  *
  * @param url
  */
case class DoPullRequest(url: String, forwardFunction: (String) => Unit) extends PullActorCommand

/**
  * PullActor Data
  */
object PullActor {
  val props = Props[PullActor]
}