package com.entrln

import akka.actor.ActorSystem
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

@Configuration
@ComponentScan(Array("com.entrln"))
class AppConfiguration {

  private def config: Config = ConfigFactory.load()

  @Bean
  @Autowired
  def getActorSystem: ActorSystem = {
    ActorSystem.create("start_app", config)
  }

  @Bean
  @Autowired
  def getExecutionContext(system: ActorSystem): ExecutionContextExecutor = system.dispatcher

  @Bean
  @Autowired
  def getDefaultReadTimeout: Timeout = Timeout(6 minute)
}
