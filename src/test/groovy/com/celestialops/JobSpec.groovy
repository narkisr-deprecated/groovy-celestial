package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*


class JobsSpec extends Specification {

  def system = new Systems()
  def job = new Jobs()
  def fixtures = new Fixtures()

  def 'Staging job'(){
    when: 
	fixtures.lazyTypeCreate()
      def id = system.create(new Fixtures().dockerSystem).id 
      def jid = job.stage(id).job
      def running = job.runningJobs().jobs.find{ it.jid == jid } 
    then: 
      id !=null
      running != null
      job.waitFor(running.tid, 121 * 1000) == false
    cleanup: 
      system.delete(id)
  }
}
