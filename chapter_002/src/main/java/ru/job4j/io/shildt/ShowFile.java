package ru.job4j.io.shildt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
Завершив работу с файлом, его нужно закрыть. Для этой цели служит метод
c l o s e ( ) , реализованный в классах
F i l e i n pu t S t r e am и F i l e Ou t pu t S t r e am:

void close () throws IOException
Закрытие файла высвобождает выделенные для него системные ресурсы , позволяя
использовать их для других файлов. Неудачный исход закрытия файла может
привести к "утечкам памяти", поскольку неиспользуемые ресурсы оперативной
памяти останутся выделенными .

На заметку! Начиная с верси и JDК 7 метод с l о s е ( ) оп
ределяется в и нтерфейсе Аu tоС l о s е аЫ е

и з пакета j ava . l a n g . И нтерфейс AutoC l o s e a Ы e
наследУет от и нтерфейса C l o s e a Ы e

и з пакета j ava . i o . Оба и нтерфейса реал изуются классам
        и п отоков ввода-вы вода , включая
классы Fi l e i npu t S t re am и Fi l eOutput S t r e am.

Следует заметить, что имеются два основных способа закрытия файла, когда
он больше не нужен. При первом , традиционном способе метод c l o s e ( ) вызывается
явно, когда файл больше не нужен . Именно такой способ применялся во
всех версиях jаvа до JDK 7, и поэтому он присутствует во всем коде, написанном
до версии JDК 7. А при втором способе применяется оператор t r y с ресурсами,
внедренный в 'версии JDК 7. Этот оператор автоматически закрывает файл , когда
он больше не нужен . И в этом случае отпадает потребность в явных вызовах
метода c l o s e ( ) . Но поскольку существует немалый объем кода, который написан
до версииJDК 7 и все еще эксплуатируется и сопровождается, то по-прежнему важно
знать и владеть традиционным способом закрытия файлов. Поэтому сначала
будет рассмотрен именно этот способ , а новый, автоматизированный способ описывается
в следующем разделе.

Чтобы прочитать данные из файла, можно воспользоваться версией метода
read ( ) , определенной в классе F i l e i npu t S t r e am. Та его версия, которая приме­
няется в представленных далее примерах, выглядит следующим образом:
int read ( ) throws IOException
Всякий раз, когда вызывается метод r e a d ( ) , он выполняет чтение одного байта
из файла и возвращает его в виде целочисленного значения. А если достигнуr
конец файла, то возвращается значение - 1 . Этот метод может сгенерировать исключение
типа I OExcep t i on .
В приведенном ниже примере программы метод read ( ) применяется дл я вво­
да из файла, содержащего текст в коде ASCII, который затем выводится на экран.
Имя файла указывается в качестве аргумента командной строки.
/* Отображение содержимо го т е кстового файл а .
* /
Чтобы восполь з о в а т ь с я э той программой , укажите
имя файла , который тре буе т с я просмотре т ь .
На пример , чтобы просмотре т ь файл TEST . TX'l' ,
введите в командной строке следующую команду :j ava ShowFile ТЕSТ . ТХТ
 */

public class ShowFile {
    public static void main(String[] args) {
        int i;
        FileInputStream fin;

        if (args.length != 1) {
            System.out.println("Использование: ShowFile input.txt");
            //убедиться что имя файла указано
            return;
        }

        try {
            //попытка открыть файл
            fin = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("невозможно открыть файл");
            return;
        }

        //теперь файл открыт и готов к чтению
        //далее из него читаются символы до тех пор, пока не
        // втретиться признак конца файла
        try {
            do {
                i = fin.read();
                if (i != -1) {
                    System.out.print((char) i);
                }
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла");
        }

        //закрыть файл
        try {
            fin.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия файла");
        }
    }
}