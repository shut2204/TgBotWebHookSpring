package com.jester.TgBotSchedulKaraga.Bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer implements CommandLineRunner {

    private final MyTelegramBot telegramBot;

    @Value("${bot.webhook.url}")
    private String webhookUrl;

    @Autowired
    public BotInitializer(MyTelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void run(String... args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            SetWebhook setWebhook = SetWebhook.builder()
                    .url(webhookUrl + telegramBot.getBotPath())
                    .build();

            // Здесь вызывается метод execute вместо registerBot
            telegramBot.execute(setWebhook);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

