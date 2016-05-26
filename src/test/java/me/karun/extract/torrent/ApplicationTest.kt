package me.karun.extract.torrent

import com.google.common.collect.ImmutableList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.contrib.java.lang.system.ExpectedSystemExit
import org.junit.contrib.java.lang.system.SystemErrRule

class ApplicationTest {
  @Rule @JvmField val errorLogCaptor = SystemErrRule().enableLog().mute()
  @Rule @JvmField val exit = ExpectedSystemExit.none()

  private val mandatoryArgs = ImmutableList.of("path-to-rar")
  private val optionalArgs = ImmutableList.builder<String>().add("--unrarPath").add("unrar path").add("--statusCode").add("0").build()
  private val allArgs = ImmutableList.builder<String>().addAll(mandatoryArgs).addAll(optionalArgs).build()

  @Test
  fun shouldParseAllArgsAndNotThrowExceptions() {
    main(array(allArgs))

    assertThat(errorLogCaptor.log).isEmpty()
  }

  @Test
  fun shouldParseWithoutOptionalArgsAndNotThrowExceptions() {
    main(array(mandatoryArgs))

    assertThat(errorLogCaptor.log).isEmpty()
  }

  @Test
  fun shouldThrowExceptionWhenMandatoryArgIsMissing() {
    exit.expectSystemExitWithStatus(1)

    main(array(optionalArgs))

    assertThat(errorLogCaptor.log.trim())
      .startsWith("usage: torrent extractor [-h]")
      .endsWith("torrent extractor: error: too few arguments")
  }

  private fun array(list: List<String>): Array<String> {
    return list.toTypedArray()
  }
}