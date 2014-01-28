package com.celestialops.client

import wslite.rest.*
import wslite.http.auth.*

@Singleton
class Celestial {
  @Delegate RESTClient client = new RESTClient('https://cassini:8443/'  )

  static {
    Celestial.getInstance().with {
    	httpClient.sslTrustAllCerts = true
    	authorization = new HTTPBasicAuthorization("admin", "changeme")
    }
  }
}
