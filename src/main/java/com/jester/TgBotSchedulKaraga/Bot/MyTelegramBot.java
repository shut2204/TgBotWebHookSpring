package com.jester.TgBotSchedulKaraga.Bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@SuppressWarnings("deprecation")
@Component
public class MyTelegramBot extends TelegramWebhookBot {

    @Value("${bot.webhook.username}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.webhook.path}")
    private String botWebhookPath;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Обработка команд и текста от пользователя

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText("Вы отправили: " + text);

            return message;
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return botWebhookPath;
    }
}
