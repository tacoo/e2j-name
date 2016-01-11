grammar CMU2Katakana;

convertKatakana returns[String result]: 
  jwords { $result = $jwords.katakana; };

jwords returns[String katakana] : j+=jword+ 
  {
    StringBuilder sb = new StringBuilder();
    for (JwordContext w : $j) {
      sb.append(w.katakana);
    }
    $katakana = sb.toString();
  }
  ;

jword returns[String katakana] : 
${katakana_pattern}
  ;

AA : 'AA' ;
AE : 'AE' ;
AH : 'AH' ;
AO : 'AO' ;
AW : 'AW' ;
AY : 'AY' ;
B  : 'B' ;
CH : 'CH' ;
D  : 'D' ;
DH : 'DH' ;
EH : 'EH' ;
ER : 'ER' ;
EY : 'EY' ;
F  : 'F' ;
G  : 'G' ;
HH : 'HH' ;
IH : 'IH' ;
IY : 'IY' ;
JH : 'JH' ;
K  : 'K' ;
L  : 'L' ;
M  : 'M' ;
N  : 'N' ;
NG : 'NG' ;
OW : 'OW' ;
OY : 'OY' ;
P  : 'P' ;
R  : 'R' ;
S  : 'S' ;
SH : 'SH' ;
T  : 'T' ;
TH : 'TH' ;
UH : 'UH' ;
UW : 'UW' ;
V  : 'V' ;
W  : 'W' ;
Y  : 'Y' ;
Z  : 'Z' ;
ZH : 'ZH' ;

WS : [ \t\r\n]+ -> skip ;

