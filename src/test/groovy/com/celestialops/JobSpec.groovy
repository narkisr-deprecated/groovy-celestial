package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*


class JobsSpec extends Specification {

; def system = new Systems()
  def job = new Jobs()
  def type = new Types()

  def 'Staging job'(){
    when: 
      type.create(new Fixtures().redisType)
      def id = system.create(new Fixtures().dockerSystem).id 
      def jid = job.stage(id).job
      def running = job.runningJobs().jobs.find{ it.jid == jid } 
    then: 
      id !=null
      running != null
      job.waitFor(running.tid, 121 * 1000) == false
    cleanup: 
      system.delete(id)
	type.delete('redis')
  }
}
