package me.karun.extract.torrent;

import com.google.common.collect.ImmutableList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemErrRule;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
  @Rule
  public final SystemErrRule errorLogCaptor = new SystemErrRule().enableLog().mute();
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  private final List<String> mandatoryArgs = ImmutableList.of("path-to-rar");
  private final List<String> optionalArgs = ImmutableList.<String>builder()
    .add("--unrarPath").add("unrar path")
    .add("--statusCode").add("0")
    .build();
  private final List<String> allArgs = ImmutableList.<String>builder()
    .addAll(mandatoryArgs)
    .addAll(optionalArgs)
    .build();

  @Test
  public void shouldParseAllArgsAndNotThrowExceptions() {
    Application.main(array(allArgs));

    assertThat(errorLogCaptor.getLog()).isEmpty();
  }

  @Test
  public void shouldParseWithoutOptionalArgsAndNotThrowExceptions() {
    Application.main(array(mandatoryArgs));

    assertThat(errorLogCaptor.getLog()).isEmpty();
  }

  @Test
  public void shouldThrowExceptionWhenMandatoryArgIsMissing() {
    exit.expectSystemExitWithStatus(1);

    Application.main(array(optionalArgs));

    assertThat(errorLogCaptor.getLog().trim())
      .startsWith("usage: " + "torrent extractor" + " [-h]")
      .endsWith("torrent extractor" + ": error: too few arguments");
  }

  private String[] array(final List<String> list) {
    return list.toArray(new String[list.size()]);
  }
}