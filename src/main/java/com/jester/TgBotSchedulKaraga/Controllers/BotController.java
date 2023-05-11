package com.jester.TgBotSchedulKaraga.Controllers;

import com.jester.TgBotSchedulKaraga.Bot.MyTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class BotController {

    private final MyTelegramBot telegramBot;

    @Autowired
    public BotController(MyTelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostMapping(value = "/karaga_schedule_bot_path")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
}

