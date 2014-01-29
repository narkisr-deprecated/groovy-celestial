package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*

class JobSpec {


class SystemSpec  extends Specification {

  def system = new Systems()
  def job = new Jobs()

  def 'Staging job'(){
    when: 
      def id = system.create(dockerSystem()).json.id 
      def jid = job.stage(id).json.job
    then: 
      job.jobs().json.jobs.find{ it.jid == jid } != null
  }
}
}
