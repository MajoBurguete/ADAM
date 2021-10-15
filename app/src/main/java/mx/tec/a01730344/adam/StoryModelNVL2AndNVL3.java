package mx.tec.a01730344.adam;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import androidx.core.content.ContextCompat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*Esta clase representa el Modelo para el nivel de dificultad "intermedio" y "difícil" del juego "Te Cuento un Cuento"*/

public class StoryModelNVL2AndNVL3 {

    //Se instancian todas las variables necesarias para el sistema del Modelo
    Context context;
    int randomStory = -1;
    int actualParagraph = 0;
    int actualQuestion = 0;
    int firstQuestion;
    int secondQuestion;
    int thirdQuestion;
    String firstParagraphAns;
    String secondParagraphAns;
    String thirdParagraphAns;
    String question;
    String firstAnswerOption;
    String secondAnswerOption;
    String thirdAnswerOption;
    List<String> firstOptionsPool;
    List<String> secondOptionsPool;
    List<String> thirdOptionsPool;
    //"Stories" segmenta los 5 cuentos en 3 párrafos diferentes cada uno
    List<List<String>> stories =
            Arrays.asList(Arrays.asList("Caperucita era una niña de # años que vivía cerca del bosque, en una casa #. Un día, # le pidió que le llevara comida a su abuelita.", "Caperucita llenó una cesta con # y salió # de su casa. Por el camino, se encontró con #.", "De repente, un lobo # apareció. Asustó a caperucita con sus #, haciéndola correr de regreso a su casa por el #."),
                    Arrays.asList("Había una vez un conejo # y una tortuga que participaron en una carrera de # vueltas por la #.", "El conejo se burlaba de la tortuga con su amiga #, diciendo: \"eres más lenta que un #, en cambio yo soy tan rápido como el #\".", "Al iniciar, el conejo rebasó a la tortuga y se sentó a #, confiado. La tortuga lo pasó, ganando #. Al llegar el conejo se enojó y #, derrotado."),
                    Arrays.asList("Había una vez una princesa con un vestido #, que tenía un perrito llamado #. Pero un día lo fue a ver # y había desaparecido.", "Fue a preguntarle # si lo había visto, pero no sabía dónde estaba. Corrió por el #, cuando encontró huellas que la guiaban al #.", "Encontró una cueva, donde estaba su perro y # más. Parecían atrapados por unas #. Al final, la princesa construyó # y los rescató."),
                    Arrays.asList("Había una vez un pastorcito llamado # que cuidaba a su rebaño en la #. Un día # estaba aburrido, cuando se le ocurrió gritar \"¡lobo!\" como broma.", "Al escucharlo, # corrieron de prisa para ayudarlo. Pero al llegar, no encontraron a ningún lobo. #, regresaron a sus #. Esto pasó varias veces.", "A la #, las personas decidieron no volver a creerle. Pero # llegó un lobo de verdad. El niño gritó de nuevo, pero nadie vino. El lobo se comió # ovejas."),
                    Arrays.asList("Había una vez un zorro que buscaba algo de comer, cuando vio a un cuervo en un # muy alto con un pedazo de # en su # pico que se le antojó.", "Decidió usar su astucia para conseguirlo, saludando al cuervo, el cual lo miró #. \"¡Gran rey cuervo!, qué # plumas y qué # figura tienes!\"", "Los halagos hicieron al cuervo abrir el pico para #, pero su comida cayó justo en la boca del zorro, quien salió corriendo hacia #. Él iba # porque había logrado su meta."));
    //"Variants" almacena las diferentes opciones existentes para construir los párrafos de las historias con pequeñas variantes.
    List<List<List<List<String>>>> variants =
            Arrays.asList(Arrays.asList(Arrays.asList(Arrays.asList("5", "8", "10"), Arrays.asList("azul", "morada", "rosa"), Arrays.asList("su mamá", "su papá", "su hermana")), Arrays.asList(Arrays.asList("fresas", "manzanas", "peras"), Arrays.asList("corriendo", "caminando", "saltando"), Arrays.asList("una rosa", "una dalia", "una margarita")), Arrays.asList(Arrays.asList("feroz", "astuto", "mentiroso"), Arrays.asList("garras", "colmillos", "aullidos"), Arrays.asList("bosque", "prado", "valle"))),
                    Arrays.asList(Arrays.asList(Arrays.asList("blanco", "gris", "café"), Arrays.asList("2", "3", "5"), Arrays.asList("montaña", "isla", "selva")), Arrays.asList(Arrays.asList("la rana", "la cebra", "la serpiente"), Arrays.asList("caracol", "perezoso", "koala"), Arrays.asList("viento", "rayo", "río")), Arrays.asList(Arrays.asList("comer", "dormir", "jugar"), Arrays.asList("un listón", "un trofeo", "una medalla"), Arrays.asList("gritó", "lloró", "suspiró"))),
                    Arrays.asList(Arrays.asList(Arrays.asList("dorado", "rojo", "morado"), Arrays.asList("Max", "Rocky", "Toby"), Arrays.asList("al jardín", "al patio", "al gran salón")), Arrays.asList(Arrays.asList("a su padre", "a su madrastra", "a su guardia"), Arrays.asList("pasillo", "puente", "jardín"), Arrays.asList("pueblo", "sótano", "pozo")), Arrays.asList(Arrays.asList("2", "5", "8"), Arrays.asList("rocas", "cajas", "tablas"), Arrays.asList("una escalera", "una rampa", "una polea"))),
                    Arrays.asList(Arrays.asList(Arrays.asList("Pablo", "Juan", "Pedro"), Arrays.asList("montaña", "pradera", "colina"), Arrays.asList("en la mañana", "en la tarde", "en la noche")), Arrays.asList(Arrays.asList("sus amigos", "sus familiares", "sus vecinos"), Arrays.asList("Frustrados", "Enojados", "Confundidos"), Arrays.asList("casas", "granjas", "actividades")), Arrays.asList(Arrays.asList("tercera vez", "cuarta vez", "quinta vez"), Arrays.asList("una tarde", "una noche", "una mañana"), Arrays.asList("3", "6", "9"))),
                    Arrays.asList(Arrays.asList(Arrays.asList("árbol", "arbusto", "poste"), Arrays.asList("queso", "manzana", "sandía"), Arrays.asList("grande", "pequeño", "ancho")), Arrays.asList(Arrays.asList("desconfiado", "inseguro", "irritado"), Arrays.asList("magníficas", "brillantes", "coloridas"), Arrays.asList("hermosa", "espléndida", "increíble")), Arrays.asList(Arrays.asList("reír", "sonreír", "cantar"), Arrays.asList("su madriguera", "su cueva", "su escondite"), Arrays.asList("contento", "saciado", "complacido"))));
    //"Questions" almacena las posibles preguntas para cada párrafo de las historias
    List<List<List<String>>> questions =
            Arrays.asList(Arrays.asList(Arrays.asList("¿Cuántos años tenía Caperucita?", "¿De qué color era la casa de Caperucita?", "¿Quién le pidió a Caperucita que llevara comida a su abuelita?"), Arrays.asList("¿Qué llevaba la cesta?", "¿Cómo salió Caperucita de su casa?", "¿Qué flor se encontró Caperucita por el camino?"), Arrays.asList("¿Cómo era el lobo que apareció?", "El lobo asustó a Caperucita con sus...", "¿Por dónde regresó Caperucita a su casa?")),
                    Arrays.asList(Arrays.asList("¿De qué color era el conejo?", "¿De cuántas vueltas era la carrera?", "La carrera se hizo en la..."), Arrays.asList("¿Quién era amiga del conejo?", "El conejo decía que la tortuga era más lenta que un…", "El conejo decía que era tan rápido como el..."), Arrays.asList("El conejo confiado se sentó a…", "¿Qué ganó la tortuga?", "Cuando perdió, el conejo se enojó y...")),
                    Arrays.asList(Arrays.asList("¿De qué color era el vestido de la princesa?", "¿Cómo se llamaba el perrito de la princesa?", "¿A dónde fue la princesa para buscar a su perrito?"), Arrays.asList("¿A quién le preguntó la princesa si había visto a su perrito?", "La princesa corrió por el…", "La princesa encontró huellas que la guiaban al..."), Arrays.asList("Además del perrito de la princesa, ¿cuántos más había en la cueva?", "Los perritos estaban atrapados por unas…", "¿Qué construyó la princesa para rescatar a los perritos?")),
                    Arrays.asList(Arrays.asList("¿Cómo se llamaba el pastorcito?", "El pastorcito cuidaba a sus ovejas en la…", "¿Cuándo hizo la broma el pastorcito?"), Arrays.asList("¿Quiénes corrieron de prisa para ayudar al pastorcito?", "¿Cómo se sintieron los que habían corrido a ayudar por no encontrar al lobo?", "Las personas regresaron a sus..."), Arrays.asList("Las personas ya no le creyeron al pastorcito a la…", "¿Cuándo llegó un lobo de verdad?", "¿Cuántas ovejas se comió el lobo?")),
                    Arrays.asList(Arrays.asList("El cuervo estaba en un…", "El cuervo tenía un pedazo de…", "¿Cómo era el pico del cuervo?"), Arrays.asList("¿Cómo miró el cuervo al zorro?", "El zorro le dijo al cuervo que sus plumas eran…", "El zorro le dijo al cuervo que tenía una figura..."), Arrays.asList("Luego de los halagos, el cuervo abrió su pico para…", "¿A dónde se fue corriendo el zorro?", "¿Cómo se sentía el zorro por haber logrado su meta?")));

    //Este constructor recibe como argumento un contexto
    public StoryModelNVL2AndNVL3(Context context) {
        this.context = context;
    }

    /*El método "generateParagraph" se encarga de construir nuevos párrafos, de acuerdo a una historia y variantes
      de forma aleatoria. Regresa el texto como un SpannableStringBuilder*/
    public SpannableStringBuilder generateNewParagraph(int difficulty) {
        //Si aún no se ha seleccionado la historia a trabajar, se elige de forma aleatoria
        if (randomStory == -1) {
            randomStory = new Random().nextInt(5);
        }
        //Se toma el párrafo de la historia seleccionada, de acuerdo al valor de la variable "actualParagraph"
        StringBuffer parrafo = new StringBuffer(stories.get(randomStory).get(actualParagraph));
        //Se inicializa un índice con valor cero para empezar a recorrer el string
        int index = 0;
        char character = parrafo.charAt(index);
        //La primera vez que se encuentra un caracter con el símbolo de gato '#', se hace la inserción de una variante aleatoria con las opciones existentes
        while (character != '#') {
            index ++;
            character = parrafo.charAt(index);
        }
        //El origen uno se registra para poder resaltar la palabra más adelante
        int origin1 = index;
        //El caracter '#' se elimina
        parrafo.deleteCharAt(index);
        int randomVariant1 = new Random().nextInt(3);
        //La variante se obtiene con el valor aleatorio creado
        String firstVariant = variants.get(randomStory).get(actualParagraph).get(0).get(randomVariant1);
        //El substring es insertado
        parrafo.insert(index, firstVariant);
        //El final uno se registra para poder resaltar la palabra más adelante
        int end1 = index + firstVariant.length();
        character = parrafo.charAt(index + 1);
        //La segunda vez y tercera vez que se encuentra el carácter '#' se realiza el mismo proceso
        while (character != '#') {
            index ++;
            character = parrafo.charAt(index);
        }
        //El origen dos se registra para poder resaltar la palabra más adelante
        int origin2 = index;
        parrafo.deleteCharAt(index);
        int randomVariant2 = new Random().nextInt(3);
        String secondVariant = variants.get(randomStory).get(actualParagraph).get(1).get(randomVariant2);
        parrafo.insert(index, secondVariant);
        //El final dos se registra para poder resaltar la palabra más adelante
        int end2 = index + secondVariant.length();
        character = parrafo.charAt(index + 1);
        while (character != '#') {
            index ++;
            character = parrafo.charAt(index);
        }
        //El origen tres se registra para poder resaltar la palabra más adelante
        int origin3 = index;
        parrafo.deleteCharAt(index);
        int randomVariant3 = new Random().nextInt(3);
        String thirdVariant = variants.get(randomStory).get(actualParagraph).get(2).get(randomVariant3);
        parrafo.insert(index, thirdVariant);
        //El final tres se registra para poder resaltar la palabra más adelante
        int end3 = index + thirdVariant.length();
        //Se crea el SpannableStringBuilder, así como sus tres modificadores de formato correspondientes
        final SpannableStringBuilder ssb = new SpannableStringBuilder(parrafo);
        /*Si la dificultad es "intermedio", se crea el SpannableStringBuilder, así como sus tres modificadores
          de formato correspondientes*/
        if (difficulty == 2) {
            final ForegroundColorSpan fcs1 = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.verde_pasto));
            final ForegroundColorSpan fcs2 = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.naranja_claro));
            final ForegroundColorSpan fcs3 = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.azul_oceano));
            /*Los modificadores de span son aplicados, tomando como referencia los orígenes y finales registrados anteriormente
              permitiendo que se resalten en colores diferentes*/
            ssb.setSpan(fcs1, origin1, end1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ssb.setSpan(fcs2, origin2, end2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ssb.setSpan(fcs3, origin3, end3, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        //La lista "answers" se encarga de almacenar las 3 variantes elegidas
        List<String> answers = Arrays.asList(firstVariant, secondVariant, thirdVariant);
        //Se selecciona un entero aleatorio para hacer referencia a la pregunta
        int randomQuestion = new Random().nextInt(3);
        /*Dependiendo del número de pregunta actual, se cambian los atributos de la pregunta como tal, la respuesta correcta y
          las opciones disponibles*/
        if (actualParagraph == 0) {
            firstQuestion = randomQuestion;
            firstParagraphAns = answers.get(randomQuestion);
            firstOptionsPool = Arrays.asList(variants.get(randomStory).get(0).get(randomQuestion).get(0), variants.get(randomStory).get(0).get(randomQuestion).get(1), variants.get(randomStory).get(0).get(randomQuestion).get(2));
        }
        if (actualParagraph == 1) {
            secondQuestion = randomQuestion;
            secondParagraphAns = answers.get(randomQuestion);
            secondOptionsPool = Arrays.asList(variants.get(randomStory).get(1).get(randomQuestion).get(0), variants.get(randomStory).get(1).get(randomQuestion).get(1), variants.get(randomStory).get(1).get(randomQuestion).get(2));
        }
        if (actualParagraph == 2) {
            thirdQuestion = randomQuestion;
            thirdParagraphAns = answers.get(randomQuestion);
            thirdOptionsPool = Arrays.asList(variants.get(randomStory).get(2).get(randomQuestion).get(0), variants.get(randomStory).get(2).get(randomQuestion).get(1), variants.get(randomStory).get(2).get(randomQuestion).get(2));
        }
        //El párrafo actual se aumenta en una unidad
        actualParagraph ++;
        //Se retorna el SpannableStringBuilder
        return ssb;
    }

    /*El método "generateQuestionAndAnswers" revisa el valor de la pregunta acual para cambiar los atributos de la pregunta como tal y
      y las opciones de respuesta múltiple. Se aplica un shuffle a cada pool para presentarlas en un orden aleatorio*/
    public void generateQuestionAndAnswers() {
        if (actualQuestion == 0) {
            question = questions.get(randomStory).get(0).get(firstQuestion);
            Collections.shuffle(firstOptionsPool);
            firstAnswerOption = firstOptionsPool.get(0);
            secondAnswerOption = firstOptionsPool.get(1);
            thirdAnswerOption = firstOptionsPool.get(2);
        }
        if (actualQuestion == 1) {
            question = questions.get(randomStory).get(1).get(secondQuestion);
            Collections.shuffle(secondOptionsPool);
            firstAnswerOption = secondOptionsPool.get(0);
            secondAnswerOption = secondOptionsPool.get(1);
            thirdAnswerOption = secondOptionsPool.get(2);
        }
        if (actualQuestion == 2) {
            question = questions.get(randomStory).get(2).get(thirdQuestion);
            Collections.shuffle(thirdOptionsPool);
            firstAnswerOption = thirdOptionsPool.get(0);
            secondAnswerOption = thirdOptionsPool.get(1);
            thirdAnswerOption = thirdOptionsPool.get(2);
        }
    }

}
