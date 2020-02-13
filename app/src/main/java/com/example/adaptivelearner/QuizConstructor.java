package com.example.adaptivelearner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizConstructor {

    List<QuizQuestions> quiz = new ArrayList<>();

    public List<QuizQuestions> getCommunicationEasyQuiz(){

        quiz.add(new QuizQuestions("Data Communication deals with the ____ of signals.","Types","Transmission","Price","Colors","Transmission","Easy"));
        quiz.add(new QuizQuestions("Data communications is a subset of?","Data Science","Telecommunication","Concurrency","Networking","Telecommunication","Easy"));
        quiz.add(new QuizQuestions("Transmission media is classified as ?","guided/unguided","strong/weak","fast/slow","smooth/choppy","guided/unguided","Easy"));
        quiz.add(new QuizQuestions("With guided media the waves are guided along a ___ path.","physical","wireless","electric","electromagnetic","CA","Easy"));
        quiz.add(new QuizQuestions("Example of Guided Media:","Twisted pair","Antenna","Wi-FI","Radio","Twisted pair","Easy"));
        quiz.add(new QuizQuestions("Example of Unguided Media:","Coaxial Cable","Optical Fibre","Wi-Fi","Twisted Pair","Wi-Fi","Easy"));
        quiz.add(new QuizQuestions("How many device share link in point-to-point?","Four","Three","Five","Two","Two","Easy"));
        quiz.add(new QuizQuestions("How many device share link in multi-point?","Less than three","More than two","One","Two","More than two","Easy"));
        quiz.add(new QuizQuestions("Both stations transmit, but one at a time:","Simplex","Full Duplex","Half Duplex","Triplex","Half Duplex","Easy"));
        quiz.add(new QuizQuestions("Both stations transmit together:","Simplex","Full Duplex","Half Duplex","Triplex","Full Duplex","Easy"));

        return  quiz;
    }

    public List<QuizQuestions> getCommunicationMediumQuiz(){

        quiz.add(new QuizQuestions("Signals are electromagnetic representations of?","Data","Memory","Transmission","Wi-FI","Data","Medium"));
        quiz.add(new QuizQuestions("Signals can be categorized as:","Direct/Analog","Alternate/Direct","Analog/Digital","Strong/Weak","Analog/Digital","Medium"));
        quiz.add(new QuizQuestions("Analog signals are propagated over?","twisted pair","coaxial cable","Wi-Fi","All of the Above","All of the Above","Medium"));
        quiz.add(new QuizQuestions("After a certain distance, analog signals will?","Attenuate  ","Strengthen","Widen","Accelerate","Attenuate","Medium"));
        quiz.add(new QuizQuestions("Positive Voltage Pulses and Negative Voltage pulses in a digital signal are represented by:","Alpha/Beta","Sigma/K","0/1","AC/DC","0/1","Medium"));
        quiz.add(new QuizQuestions("Analog data can converted to digital using a ___","Codec","Hub","Modem","Encryptor","Codec","Medium"));
        quiz.add(new QuizQuestions("Advantage of Digital Signals over Analog ?","Less Noise","Faster","Less Attenuation","Wider","Less Noise","Medium"));
        quiz.add(new QuizQuestions("Digital bandwidth is determined using ?","Bit Interval/Rate","Strength","Attenuation","Frequency","Bit Interval/Rate","Medium"));
        quiz.add(new QuizQuestions("Digital signal with a bit interval of 90 microseconds, what is its bandwidth ?","1/(90*10^-6)","1/(60*10^-9)","1*(90*10^-9)","1*(90*10^-6)","1/(90*10^-6","Medium"));
        quiz.add(new QuizQuestions("Digital bandwidth is typically expressed in?","Bits per Second","Lambda","Milliseconds","Bytes","Bits per Second","Medium"));

        return  quiz;
    }

    public List<QuizQuestions> getCommunicationHardQuiz(){

        quiz.add(new QuizQuestions("A twisted pair consists of two ___ copper wires.","insulated","conductive","thick","electrified","insulated","Hard"));
        quiz.add(new QuizQuestions("The twisting in a twisted pair tends to decrease the ?","Interference","Insulation","Conductivity","Thickness","Interference","Hard"));
        quiz.add(new QuizQuestions("Twisted Pair is susceptible to:","Interference","Noise","Both","None","Both","Hard"));
        quiz.add(new QuizQuestions("Uses total internal reflection to transmit light?","Twisted Pair","Optical Fibre","Coaxial Cable","None","Optical Fibre","Hard"));
        quiz.add(new QuizQuestions("Coupling of signal from one pair to another is known as:","Fading","Echo","Crosstalk","Feedback","Crosstalk","Hard"));
        quiz.add(new QuizQuestions("Coaxial cable supports ___ bandwidths than twisted pair.","Higher","Lower","Equal","Separate","Higher","Hard"));
        quiz.add(new QuizQuestions("Coaxial cable resists EMI better than bandwidths than?","Twisted Pair","Optical Fibre","Both","None","Twisted Pair","Hard"));
        quiz.add(new QuizQuestions("Repeater spacing required for Fibre Optic cable?","1 KM","4 KM","7 KM",">10 KM",">10 KM","Hard"));
        quiz.add(new QuizQuestions("Optical fibre is suitable for?","Long Distances","Short Distances","Both","Unlimited","Short Distances","Hard"));
        quiz.add(new QuizQuestions("Optical fibre is immune to?","Cutting","Disconnecting","Interference","Delay","Interference","Hard"));

        return  quiz;
    }

    public List<QuizQuestions> getCommunicationEvaluateQuiz(){

        quiz.add(new QuizQuestions("Data ___ occurs between a transmitter & receiver ","correction","transmission","splitting","sorting","transmission","Evaluate"));
        quiz.add(new QuizQuestions("Unguided media is also known as ____ media.","wired","wireless","fast","reliable","wireless","Evaluate"));
        quiz.add(new QuizQuestions("Unguided media can propagate through ?","Air","Vacuum","Seawater","All of the above","All of the above","Evaluate"));
        quiz.add(new QuizQuestions("Signals are only transmitted in one direction:","Half Duplex","Simplex","Full Duplex","None","Simplex","Evaluate"));
        quiz.add(new QuizQuestions(" ___ is the physical propagation of the signal along a suitable medium.","Signaling","Transmitting","Crosstalk","Wi-FI","Signaling","Evaluate"));
        quiz.add(new QuizQuestions("Data are propagated from one point to another by means of what signals?","analog","electromagnetic","digital","All of the above","All of the above","Evaluate"));
        quiz.add(new QuizQuestions("Analog signals require ___ for long distances.","modems","hubs","repeaters","decoders","repeaters","Evaluate"));
        quiz.add(new QuizQuestions("Twisted Pair cable needs a repeater for every:","1 KM","2-3 KM","9-10 KM","5 KM","2-3 KM","Evaluate"));
        quiz.add(new QuizQuestions("Unsuitable for very high-speed data transmission?","Twisted Pair","Optical Fibre","Both","None","Twisted Pair","Evaluate"));
        quiz.add(new QuizQuestions("Relatively complex to configure and install.","Optical Fibre","Coaxial Cable","Twisted Pair","All of the above","Optical Fibre","Evaluate"));

        return  quiz;
    }

}
