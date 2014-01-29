package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*


class JobsSpec extends Specification {

  def system = new Systems()
  def job = new Jobs()

  def 'Staging job'(){
    when: 
      def id = system.create(new Fixtures().dockerSystem).json.id 
      def jid = job.stage(id).json.job
    then: 
      job.jobs().json.jobs.find{ it.jid == jid } != null
    // cleanup: 
      // system.delete(id)

  }
}
