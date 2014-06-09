package com.celestialops.client

/*
 * commonly used flows
 */
class Flows {
  def system = new Systems()
  def job = new Jobs()
  def static MIN = 60 * 1000

  def stageFlow(s, timeout=MIN) {
    def id = system.create(s).id 
    def jid = job.stage(id).job
    def running = job.runningJobs().jobs.find{ it.jid == jid } 
    assert job.waitFor(running.tid, timeout) == true
    id
  }

  def actionFlow(action, id, args, timeout=MIN) {
    def jid = job.launch(action, id, args).job
    def running = job.runningJobs().jobs.find{ it.jid == jid } 
    assert job.waitFor(running.tid, timeout) == true
  }

  def destroyFlow(id, timeout=MIN) {
    def jid = job.destroy(id).job
    def running = job.runningJobs().jobs.find{ it.jid == jid } 
    assert job.waitFor(running.tid, timeout) == true
  }
}
