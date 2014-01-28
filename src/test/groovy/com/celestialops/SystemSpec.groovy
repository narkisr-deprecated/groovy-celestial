package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*


class SystemSpec  extends Specification {

  def system = new Systems()

  def 'System creation'(){
   expect:
    system.get(1).json.owner == 'ronen'
  }
}
