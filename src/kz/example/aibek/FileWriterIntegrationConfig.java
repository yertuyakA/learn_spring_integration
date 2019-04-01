package kz.example.aibek;
/** Использование Spring Integration с DSL (domain specific language)
 * Захват всего потока одним bean методом
 * Класс IntegrationFlows инициирует API интерфейс builder, из которого вы можете объявить поток
 */



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class FileWriterIntegrationConfig {

    @Bean
    public IntegrationFlow fileWriterFlow(){
        return IntegrationFlows
                .from(MessageChannels.direct("textInChannel"))  //входящий channel
                .<String, String>transform(t->t.toUpperCase())  //объявлем a transformer
                .handle(Files.outboundAdapter(new File("tmp"))  //handler для записи в файл
                .fileExistsMode(FileExistsMode.APPEND).appendNewLine(true))
                .get();
    }

}
