package network.jw.servicesheet;

import android.app.Activity;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.graphics.image.JPEGFactory;
import com.tom_roush.pdfbox.pdmodel.graphics.image.LosslessFactory;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class inputFormActivity extends AppCompatActivity {
    String result = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Log.d("TYPE------>", data.getStringExtra("type"));
                result = data.getStringExtra("type");
            }
            else {
                result = "";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        PDFBoxResourceLoader.init(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);
        File f = new File("/sdcard/ServiceSheetPDFs");

        if (!f.exists()) {
            f.mkdir();
        }

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(inputFormActivity.this, typeSelection.class);
                startActivityForResult(intent, 1);
                Log.d("TEST", result.toString());
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(inputFormActivity.this, Contacts.class);
                startActivityForResult(intent, 1);
                Log.d("TEST", result.toString());
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                        try {
                            PDFBoxResourceLoader.init(getApplicationContext());
                            final String editText = ((EditText) findViewById(R.id.editText)).getText().toString();
                            final String editText2 = ((EditText) findViewById(R.id.editText2)).getText().toString();
                            final String editText5 = ((EditText) findViewById(R.id.editText5)).getText().toString();
                            final String editText6 = ((EditText) findViewById(R.id.editText6)).getText().toString();
                            final String editText7 = ((EditText) findViewById(R.id.editText7)).getText().toString();
                            final String editText3 = ((EditText) findViewById(R.id.editText3)).getText().toString();
//                            final Integer type = 3; // TODO: SETUP type VARIABLE AND NEW ACTIVITY
                            pdfdesign(editText, editText2, result, editText5, editText6, editText7, editText3, timeStamp);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

//                try {
//                    final String editText = ((EditText)findViewById(R.id.editText)).getText().toString();
//                    final String editText2 = ((EditText)findViewById(R.id.editText2)).getText().toString();
//                    final String editText5 = ((EditText)findViewById(R.id.editText5)).getText().toString();
//                    final String editText6 = ((EditText)findViewById(R.id.editText6)).getText().toString();
//                    final String editText7 = ((EditText)findViewById(R.id.editText7)).getText().toString();
//                    final String editText3 = ((EditText)findViewById(R.id.editText3)).getText().toString();
//                    final Integer type = 3; // TODO: SETUP type VARIABLE AND NEW ACTIVITY
//                    pdfdesign(editText, editText2, type, editText5, editText6, editText7, editText3);
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    // String client, String addr, String type, String dets, String recs, String matreq, String matsup WILL BE THE ARGS
    public void pdfdesign(String client, String addr, String type, String dets, String recs, String matreq, String matsup, String timeStamp) throws IOException {
        PDFBoxResourceLoader.init(getApplicationContext());
        PDDocument document = new PDDocument();
        PDPage pg = new PDPage(PDRectangle.A4);
        document.addPage(pg);
        PDPage pg1 = document.getPage(0);
        PDPageContentStream conStr = new PDPageContentStream(document, pg1);

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//        String client = "James Whybrow";
//        String addr = "192 Sinclair Road, Chingford, London, E4 8PT";
//        Integer type = 3;
//        String dets = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";
//        String recs = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip";
//        String matreq = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor";
//        String matsup = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor";

        conStr.beginText();
        conStr.setFont(PDType1Font.HELVETICA, 15);
        conStr.newLineAtOffset(40, 554);

        conStr.endText();


//        Log.d("PDFBOX", "Line gen. started");
//        conStr.moveTo(10, 10); // Vert. outer border L
//        conStr.lineTo(10,690);
//        conStr.moveTo(585, 10); // Vert. outer border R
//        conStr.lineTo(585,690);
//        conStr.moveTo(10, 10); // Hor. OB B
//        conStr.lineTo(585,10);
//        conStr.moveTo(10, 690); // Hor. OB T
//        conStr.lineTo(585,690);
//        conStr.closeAndStroke(); // END OF LINES
//        Log.d("PDFBOX", "Line gen. finished");


        Log.d("PDFBOX", "Image gen. started");
        InputStream stream = getAssets().open("afsconv.jpg");
        PDImageXObject fromstrm = JPEGFactory.createFromStream(document, stream);
//        PDImageXObject fromstrm = LosslessFactory.createFromImage()
        // TODO Set template to PNG via LosslessFactory as above. Convert file from assets using a temp file
        conStr.drawImage(fromstrm, 0, 0);
        Log.d("PDFBOX", "Image gen. finished");


        Log.d("PDFBOX", "Text gen. started");

        conStr.beginText(); // START OF TEXT
        conStr.setFont(PDType1Font.HELVETICA, 15);
        conStr.newLineAtOffset(313, 704);
        conStr.showText(client);
        conStr.endText(); // END OF TEXT

        conStr.beginText(); // START OF TEXT
        conStr.setFont(PDType1Font.HELVETICA, 15);
        conStr.newLineAtOffset(100, 704);
        conStr.showText(date);
        conStr.endText(); // END OF TEXT

        conStr.beginText(); // START OF TEXT
        conStr.setFont(PDType1Font.HELVETICA, 18);
        conStr.newLineAtOffset(40, 654);
        conStr.showText(addr);
        conStr.endText(); // END OF TEXT

        conStr.beginText(); // START OF TEXT
        conStr.setFont(PDType1Font.HELVETICA, 18);
        //conStr.newLineAtOffset(42, 554);

        Integer wplamt = 9;
        String[] words = dets.split("\\s+");
        //conStr.newLineAtOffset(40, 564);
        conStr.newLineAtOffset(40, 579);
        for (int x=0; x<words.length; x=x+9) { // MAY NEED CHANGING TO x+10
            conStr.newLineAtOffset(0, -20); // MAY NEED TO SET AS 40, 564-x*20? 40 must be removed after second loop also.
            String[] txt = Arrays.copyOfRange(words, x, x+9);
            StringBuilder builder = new StringBuilder();
            for (String value : txt) {
                if (value != null) {
                    builder.append(" " + value);
                }
            }
            String finaltxt = builder.toString();
            conStr.showText(finaltxt);
        }



        // TODO: (1) SET UP SO THAT IT COUNTS THE WORDS AND DIVIDES IT BY 9 (INTEGRAL DIVISION) AND THEN SPLITS THE STRING INTO X PARTS WITH X BEING THE OUTPUT OF WORDS/9
        // TODO: (2) (SEE PHONE) SPLIT STRING INTO ARRAY OF EACH WORD, GET LENGTH THAT WAY, INTEGER DIV. BY 9 (or however many avg. words per line) THEN FOR LOOP THE OUTPUT OF THAT WITH NEWLINES AND TEXT OF EACH SECTION OF THE ORIGINAL ARRAY AND OFFSET EACH BY -20
//        conStr.showText(dets);
        conStr.endText(); // END OF TEXT


        conStr.beginText(); // START OF TEXT
        conStr.setFont(PDType1Font.HELVETICA, 15);
        conStr.newLineAtOffset(40, 311);

        String[] words2 = recs.split("\\s+");
        for (int x=0; x<words2.length; x=x+9) { // MAY NEED CHANGING TO x+10
            conStr.newLineAtOffset(0, -20); // MAY NEED TO SET AS 40, 564-x*20? 40 must be removed after second loop also.
            String[] txt = Arrays.copyOfRange(words2, x, x+9);
            StringBuilder builder = new StringBuilder();
            for (String value : txt) {
                if (value != null) {
                    builder.append(" " + value);
                }
            }
            String finaltxt = builder.toString();
            conStr.showText(finaltxt);
        }

        conStr.endText(); // END OF TEXT

        conStr.beginText(); // START OF TEXT
        conStr.setFont(PDType1Font.HELVETICA, 15);
        conStr.newLineAtOffset(40, 180);

        String[] words3 = matreq.split("\\s+");
        for (int x=0; x<words3.length; x=x+4) { // MAY NEED CHANGING TO x+10
            conStr.newLineAtOffset(0, -20); // MAY NEED TO SET AS 40, 564-x*20? 40 must be removed after second loop also.
            String[] txt = Arrays.copyOfRange(words3, x, x+4);
            StringBuilder builder = new StringBuilder();
            for (String value : txt) {
                if (value != null) {
                    builder.append(" " + value);
                }
            }
            String finaltxt = builder.toString();
            conStr.showText(finaltxt);
        }

        conStr.endText(); // END OF TEXT

        conStr.beginText(); // START OF TEXT
        conStr.setFont(PDType1Font.HELVETICA, 15);
        conStr.newLineAtOffset(300, 180);

        String[] words4 = matsup.split("\\s+");
        for (int x=0; x<words4.length; x=x+4) { // MAY NEED CHANGING TO x+10
            conStr.newLineAtOffset(0, -20); // MAY NEED TO SET AS 40, 564-x*20? 40 must be removed after second loop also.
            String[] txt = Arrays.copyOfRange(words4, x, x+4);
            StringBuilder builder = new StringBuilder();
            for (String value : txt) {
                if (value != null) {
                    builder.append(" " + value);
                }
            }
            String finaltxt = builder.toString();
            conStr.showText(finaltxt);
        }

        conStr.endText(); // END OF TEXT



//        conStr.beginText(); // START OF TEXT
//        conStr.setFont(PDType1Font.HELVETICA, 18);
//        conStr.newLineAtOffset(42, 554);
//        conStr.newLineAtOffset(0, -20);
//        conStr.showText("TEST");
//        conStr.endText(); // END OF TEXT

        conStr.setNonStrokingColor(0,1,1,0); // START OF LINES
        conStr.setLineWidth(2);
//        Integer testtypeid = Integer.parseInt(Character.toString(type.charAt(1)));

        switch (type) {
            case "Callout": conStr.moveTo(48, 608); conStr.lineTo(106, 608); break;
            case "Service": conStr.moveTo(134, 608); conStr.lineTo(197, 608); break;
            case "Installation": conStr.moveTo(216, 608); conStr.lineTo(294, 608); break;
            case "Commission": conStr.moveTo(300, 608); conStr.lineTo(379, 608); break;
            case "Return Visit": conStr.moveTo(386, 608); conStr.lineTo(465, 608); break;
            case "Survey": conStr.moveTo(485, 608); conStr.lineTo(541, 608); break;
            case "": conStr.moveTo(134, 608); conStr.lineTo(197, 608); break;
        }
        conStr.closeAndStroke();

        Log.d("PDFBOX", "Text gen. finished");

        conStr.close();
        document.save("/sdcard/ServiceSheetPDFs/"+timeStamp+".pdf");
        document.close();
    }
}
