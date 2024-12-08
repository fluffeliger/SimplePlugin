# SimplePlugin
A simple plugin


## Commands

### `/simple <...>`

This command throws you in the air!

## Config

You can configure the permission for the commands and also the return messages!
The default Config looks like this:

```yml
messages:
  cmd-need-player: "<red>Du musst ein Spieler sein um diesen Befehl ausführen zu können!"
  missing-permission: "<red>Dazu fehlen dir die Rechte"

  simple-command-feedback: "<gray>Das ist ein einfacher Befehl mit <aqua>%s</aqua> argumenten"

permissions:
  commands:
    simple:
      - simple.command.simple
```