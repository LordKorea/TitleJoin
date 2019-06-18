# TitleJoin

This plugin provides a simple way to show a simple title message to members on
join.

## Installation

1. Drop the downloaded jar into your `plugins/` folder

2. Restart your server

3. Modify the configuration file to your liking

4. Reload the configuration via `/titlejoin reload`

## Configuration

```
join-message-title: "&aWelcome %s!"  # The title message (%s is replaced by the player's name)
join-message-subtitle: "&eWe welcome you on GenericServer!"  # The sub-title message (%s is replaced by the player's name)
join-message-delay: 5  # The delay (in ticks) after which the title message will be shown on join
join-message-fade-in: 10  # The fade-in timer (cf. /title command)
join-message-stay: 70  # The stay timer (cf. /title command)
join-message-fade-out: 20  # The fade-out timer (cf. /title command)
```

Advanced configuration: If you need to specify the player name multiple times in
a message, you can use `%1$s` instead of `%s`.

## Permissions

`titlejoin.reload` -- grants permission to `/titlejoin reload`
