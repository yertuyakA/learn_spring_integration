package kz.example.aibek;
/** Нужно создать способ для отправки данных в integration flow, чтобы их можно были записать в файл
 * Для этого нужно создать интетфейс шлюза (gateway)
 * Преобразования вызов методов в сообщения
 * */

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "textInChannel")  //Объявляем
public interface FileWriterGateway {

    void writeToFile(@Header (FileHeaders.FILENAME) String filename, String data);

}
/*defaultRequestChannel в MessagingGateway указывает, что любые сообщения, возникающие в результате вызова
 методов интерфейса, должны отправляться в данный канал сообщений. В этом случае вы заявляете, что любые сообщения,
 полученные в результате вызова writeToFile(), должны отправляться на канал, имя которого textInChannel
 */