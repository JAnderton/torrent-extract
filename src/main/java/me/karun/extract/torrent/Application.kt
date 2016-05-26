package me.karun.extract.torrent;

import net.sourceforge.argparse4j.ArgumentParsers
import net.sourceforge.argparse4j.inf.ArgumentParser

fun main(args: Array<String>) {
  val ns = buildArgumentParser().parseArgsOrFail(args)
}

private fun buildArgumentParser(): ArgumentParser {
  val parser = ArgumentParsers
    .newArgumentParser("torrent extractor")
    .description("Extracts torrents")

  parser.addArgument("--unrarPath")
    .metavar("unrarPath")
    .setDefault("C:\\Program Files\\WinRAR\\UnRAR.exe")
    .help("Path to unrar executable")

  parser.addArgument("pathToRar")
    .nargs(1)
    .metavar("pathToRar")
    .help("Path to rar file")

  parser.addArgument("--statusCode")
    .metavar("statusCode")
    .setDefault(0)
    .help("uTorrent status code (read more in uTorrent guide)")

  return parser
}