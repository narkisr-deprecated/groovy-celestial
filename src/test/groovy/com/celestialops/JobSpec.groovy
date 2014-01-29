package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*


class JobsSpec extends Specification {

  def system = new Systems()
  def jobs = new Jobs()

  def 'Staging job'(){
    when: 
      def id = system.create(new Fixtures().dockerSystem).json.id 
      def jid = jobs.stage(id).json.job
      def running = jobs.listJobs().json.jobs.find{ it.jid == jid } 
    then: 
      running != null
      jobs.waitUntil(running.tid, Jobs.State.Erroneous, 60 * 1000) == true
    cleanup: 
      system.delete(id)
  }
}
