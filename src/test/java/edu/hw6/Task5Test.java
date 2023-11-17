package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {

    @Test
    @DisplayName("Название статьи")
    void titleTopic() {
        HackerNews hackerNews = new HackerNews();

        long newsId = 37570037;
        String newsTitle = hackerNews.news(newsId);

        assertEquals("JDK 21 Release Notes", newsTitle);
    }

    @Test
    @DisplayName("Статьи нет")
    void topicNotExistTopic() {
        HackerNews hackerNews = new HackerNews();

        long newsId = 3757003754537560L;

        Throwable thrown = catchThrowable(() -> {
            hackerNews.news(newsId);
        });
        assertThat(thrown).hasMessage("Названия нет");
    }

}
