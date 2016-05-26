package me.karun.extract.torrent;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.Namespace;

public class Application {
  public static void main(String[] args) {
    final Namespace ns = buildArgumentParser().parseArgsOrFail(args);
  }

  private static ArgumentParser buildArgumentParser() {
    final ArgumentParser parser = ArgumentParsers.newArgumentParser("torrent extractor")
      .description("Extracts torrents");

    parser.addArgument("--unrarPath")
//      .nargs("?")
      .metavar("unrarPath")
      .setDefault("C:\\Program Files\\WinRAR\\UnRAR.exe")
      .help("Path to unrar executable");

    parser.addArgument("pathToRar")
      .nargs(1)
      .metavar("pathToRar")
      .help("Path to rar file");

    parser.addArgument("--statusCode")
//      .nargs("?")
      .metavar("statusCode")
      .setDefault(0)
      .help("uTorrent status code (read more in uTorrent guide)");

    return parser;
  }
}
