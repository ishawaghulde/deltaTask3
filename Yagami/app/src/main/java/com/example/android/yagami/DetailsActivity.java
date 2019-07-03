package com.example.android.yagami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.android.yagami.MainActivity.KEY_1;
import static com.example.android.yagami.MainActivity.KEY_2;

public class DetailsActivity extends AppCompatActivity {
    TypeWriter textView;
    TextView head;
    RelativeLayout relLayout;
    private String info;
    private boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString(KEY_1);
        final int pos = Integer.parseInt(str);
        String place = bundle.getString(KEY_2);
        textView = findViewById(R.id.textView);
        head = findViewById(R.id.head);
        relLayout = findViewById(R.id.relLayout);
        textView.setText("");
        head.setText(place);
        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = true;
                setDetails(pos);
                writeText(click);
            }
        });
        setDetails(pos);
        writeText(click);
    }


    private void setDetails(int pos){
        switch(pos) {
            case 0:
                info = "Avon and Somerset Constabulary is the territorial police force responsible for law enforcement in the county of Somerset and the now-defunct county of Avon, which includes the city and county of Bristol and the unitary authorities of Bath and North East Somerset, North Somerset and South Gloucestershire.";
                break;

            case 1:
                info = "Bedfordshire Police, is the territorial police force responsible for policing the ceremonial county of Bedfordshire in England, which includes the unitary authorities of Bedford, Central Bedfordshire and Luton. Its headquarters are in Kempston, Bedfordshire.";
                break;

            case 2:
                info = "Cambridgeshire Constabulary is the territorial police force responsible for law enforcement within the ceremonial county of Cambridgeshire in the United Kingdom. In addition to the non-metropolitan county, the Police area includes the city of Peterborough, which became a unitary authority area in 1998. ";
                break;

            case 3:
                info = "Cheshire Constabulary is the territorial police force responsible for policing the English unitary authorities of Cheshire East, Cheshire West and Chester, Halton (including Runcorn and Widnes) and Warrington.";
                textView.animateText(info);
                break;

            case 4:
                info = "The City of London Police is the territorial police force responsible for law enforcement within the City of London, including the Middle and Inner temples. The force responsible for law enforcement within the remainder of the London region, outside the City, is the much larger Metropolitan Police Service, a separate organisation.";
                break;

            case 5:
                info = "Cleveland Police is the territorial police force responsible for policing the area of former county of Cleveland in north east England. ";
                break;

            case 6:
                info = "Cumbria Constabulary is the territorial police force in England covering Cumbria.";
                break;

            case 7:
                info = "Derbyshire Constabulary is the territorial police force responsible for policing the county of Derbyshire, England.";
                break;

            case 8:
                info = "Devon and Cornwall Police is the territorial police force responsible for policing the counties of Devon and Cornwall, including the unitary authority areas of Plymouth, Torbay and the Isles of Scilly.";
                break;

            case 9:
                info = "Dorset Police is the territorial police force responsible for policing the English county of Dorset in the south-west of England.";
                break;

            case 10:
                info = "Durham Constabulary is the territorial police force responsible for policing the non-metropolitan county of County Durham and the unitary authority of Darlington.";
                break;

            case 11:
                info = "Essex Police is a territorial police force responsible for policing the county of Essex, in the east of England.";
                break;

            case 12:
                info = "Gloucestershire Constabulary is the territorial police force responsible for policing the non-metropolitan county of Gloucestershire in England (South Gloucestershire is covered by Avon and Somerset Constabulary).";
                textView.animateText(info);
                break;

            case 13:
                info = "Greater Manchester Police (GMP) is the police force responsible for law enforcement within the metropolitan county of Greater Manchester in North West England.";
                break;

            case 14:
                info = "Hampshire Constabulary is the territorial police force responsible for policing the counties of Hampshire and the Isle of Wight in South East England.";
                break;


            case 15:
                info = "Hertfordshire Constabulary is the territorial police force responsible for policing the county of Hertfordshire in England. Its headquarters is in Welwyn Garden City.";
                break;

            case 16:
                info = "Humberside Police is the territorial police force responsible for policing an area covering the East Riding of Yorkshire, the city of Kingston upon Hull, North East Lincolnshire and North Lincolnshire.";
                break;

            case 17:
                info = "Kent Police is the territorial police force for Kent in England.";
                break;

            case 18:
                info = "Lancashire Constabulary is the territorial police force responsible for policing the ceremonial county of Lancashire in North West England.";
                break;

            case 19:
                info = "Leicestershire Police[2] is the territorial police force responsible for policing Leicestershire and Rutland in England. Its headquarters are at Enderby, Leicestershire.";
                break;

            case 20:
                info = "Lincolnshire Police is the territorial police force covering the non-metropolitan county of Lincolnshire in the East Midlands of England.";
                break;

            case 21:
                info = "Merseyside Police is the territorial police force responsible for policing Merseyside in North West England.";
                break;

            case 22:
                info = "The Metropolitan Police Service (MPS), formerly and still commonly known as the Metropolitan Police and informally as the Met, Scotland Yard or \"the Yard\", is the territorial police force responsible for law enforcement in the Metropolitan Police District, which currently consists of the 32 London boroughs. The MPD does not include the \"square mile\" of the City of London, which is policed by the much smaller City of London Police.";
                break;

            case 23:
                info = "Norfolk Constabulary is the territorial police force responsible for the county of Norfolk in England.";
                break;

            case 24:
                info = "Northamptonshire Police (colloquially known as Northants Police) is the territorial police force responsible for policing the county of Northamptonshire in the East Midlands of England, in the United Kingdom.";
                break;

            case 25:
                info = "Northumbria Police is the territorial police force responsible for policing the areas of Northumberland and Tyne and Wear in North East England.";
                break;

            case 26:
                info = "North Yorkshire Police is the territorial police force covering the non-metropolitan county of North Yorkshire and the unitary authority of York in northern England.";
                break;

            case 27:
                info = "Nottinghamshire Police is the territorial police force responsible for policing the shire county of Nottinghamshire and the unitary authority of Nottingham in the East Midlands of England.";
                break;

            case 28:
                info = "South Yorkshire Police is the territorial police force responsible for policing South Yorkshire in England.";
                break;

            case 29:
                info = "Staffordshire Police is the territorial police force responsible for policing Staffordshire and Stoke-on-Trent in the West Midlands of England.";
                break;

            case 30:
                info = "Suffolk Constabulary is the territorial police force responsible for policing Suffolk in East Anglia, England.";
                break;

            case 31:
                info = "Surrey Police is the territorial police force responsible for policing the county of Surrey in South East England.";
                break;

            case 32:
                info = "Sussex Police is the territorial police force responsible for policing the county of Sussex in southern England (consisting of East Sussex, West Sussex and the city of Brighton and Hove). ";
                break;

            case 33:
                info = "Thames Valley Police, formerly known as Thames Valley Constabulary, is the territorial police force responsible for policing the Thames Valley area covered by the counties of Berkshire, Buckinghamshire and Oxfordshire.";
                break;

            case 34:
                info = "Warwickshire Police is the territorial police force responsible for policing Warwickshire in England. It was known as Warwickshire Constabulary until 2001.";
                break;

            case 35:
                info = "West Mercia Police, formerly known as West Mercia Constabulary, is the territorial police force responsible for policing the counties of Herefordshire, Shropshire (including Telford and Wrekin) and Worcestershire in England. ";
                break;

            case 36:
                info = "West Midlands Police is the territorial police force responsible for policing the metropolitan county of West Midlands in England.";
                break;

            case 37:
                info = "West Yorkshire Police is the territorial police force responsible for policing West Yorkshire in England.";
                break;

            case 38:
                info = "Wiltshire Police, formerly known as Wiltshire Constabulary, is the territorial police force responsible for policing the county of Wiltshire (including the Borough of Swindon) in the south-west of England.";
                break;

            case 39:
                info = " Dyfed–Powys Police (Welsh: Heddlu Dyfed–Powys) is the territorial police force responsible for policing Carmarthenshire, Ceredigion and Pembrokeshire (which make up the former administrative area of Dyfed) and the unitary authority of Powys (covering Brecknockshire, Radnorshire and Montgomeryshire), in Wales.";
                break;

            case 40:
                info = "Gwent Police (Welsh: Heddlu Gwent) is a territorial police force in Wales, responsible for policing the local authority areas of Blaenau Gwent, Caerphilly, Monmouthshire, Newport and Torfaen.";
                break;

            case 41:
                info = "North Wales Police (Welsh: Heddlu Gogledd Cymru) is the territorial police force responsible for policing North Wales. ";
                break;

            case 42:
                info = "South Wales Police (Welsh: Heddlu De Cymru) is one of the four territorial police forces in Wales. Its headquarters is in Bridgend.\n" +
                        "\n" +
                        "It covers most of the historic county of Glamorgan, including Wales' capital city, Cardiff, as well as Bridgend, Merthyr Tydfil, Swansea, and the western South Wales Valleys.";
                break;

        }


    }

    private void writeText(boolean click){
        if(!click){
            textView.animateText(info);
        }
        else{
            textView.setText("");
            textView.setChars();
            textView.setText(info);
        }
    }
}
