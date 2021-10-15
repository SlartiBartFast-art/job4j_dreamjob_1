package ru.job4j.dream.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServlet;

/**
 * 1. Загрузка и скачивание файла. [#154183 #207844]
 * В это уроке мы познакомимся с возможностью servlet загружать файлы на сервер и отдавать их клиенту.

 *Http протокол позволяет передавать файлы между клиентом и сервером.
 * Давайте создадим DownloadServlet.
 * Пока наш сервлет ничего не делает. Задача этого сервлета отдать файл, который лежит на сервере.
 */
public class DownloadServlet extends HttpServlet {
    /**
     * Теперь добавим в сервлете вывод на консоль содержимого папки c:\images
     *   for (File file : new File("c:\\images\\").listFiles()) {
     *             System.out.println(file.getAbsolutePath());
     *             Браузер отобразить содержимое файла. Почему так произошло?      *
     * HTTP - это текстовый протокол. По умолчанию все данные в протоколе считаются браузером, как текст.     *
     * Чтобы указать, что сервер отправляет файл нужно становить тип данных.     *
     * resp.setContentType("application/octet-stream");
     * resp.setHeader("Content-Disposition", "attachment; filename=\"" + users.getName() + "\"");
     * 1. Мы выставляем заголовок ответа в протоколе. Таким образом мы сообщаем браузеру, что будем отправлять файл.
     * 2. Открываем поток и записываем его в выходной поток servlet.
         * resp.getOutputStream().write(in.readAllBytes());
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name") + ".png";
        System.out.println("NAME + " + name);
        File downloadFile = null;
        for (File file : new File("c:\\images\\").listFiles()) {
            System.out.println("File name : " + file.getName());
            if (name.equals(file.getName())) {
                downloadFile = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)){
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
