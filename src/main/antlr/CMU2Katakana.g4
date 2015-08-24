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
    B  AA { $katakana = "バ"; }
  | B  AE { $katakana = "ベ"; }
  | B  AH { $katakana = "バー"; }
  | B  AO { $katakana = "バオ"; }
  | B  AW { $katakana = "バウ"; }
  | B  AY { $katakana = "バイ"; }
  | B  IH { $katakana = "ビー"; }
  | B  IY { $katakana = "ビ"; }
  | B  UH { $katakana = "ブ"; }
  | B  UW { $katakana = "ブ"; }
  | B  EH { $katakana = "ベ"; }
  | B  ER { $katakana = "バー"; }
  | B  EY { $katakana = "ベイ"; }
  | B  OW { $katakana = "ボウ"; }
  | B  OY { $katakana = "ボイ"; }
  | B     { $katakana = "ブ"; }
  | CH AA { $katakana = "チャ"; }
  | CH AE { $katakana = "チェ"; }
  | CH AH { $katakana = "チャー"; }
  | CH AO { $katakana = "チャオ"; }
  | CH AW { $katakana = "チャウ"; }
  | CH AY { $katakana = "チャイ"; }
  | CH IH { $katakana = "チー"; }
  | CH IY { $katakana = "チ"; }
  | CH UH { $katakana = "チュ"; }
  | CH UW { $katakana = "チュ"; }
  | CH EH { $katakana = "チェ"; }
  | CH ER { $katakana = "チャー"; }
  | CH EY { $katakana = "チェイ"; }
  | CH OW { $katakana = "チョウ"; }
  | CH OY { $katakana = "チョイ"; }
  | CH    { $katakana = "チ"; }
  | D  AA { $katakana = "ジャ"; }
  | D  AE { $katakana = "デェ"; }
  | D  AH { $katakana = "ジャー"; }
  | D  AO { $katakana = "ジャオ"; }
  | D  AW { $katakana = "ジャウ"; }
  | D  AY { $katakana = "ジャイ"; }
  | D  IH { $katakana = "ディー"; }
  | D  IY { $katakana = "ディ"; }
  | D  UH { $katakana = "ジュ"; }
  | D  UW { $katakana = "ジュ"; }
  | D  EH { $katakana = "デェ"; }
  | D  ER { $katakana = "ジャー"; }
  | D  EY { $katakana = "デェイ"; }
  | D  OW { $katakana = "ジョウ"; }
  | D  OY { $katakana = "ジョイ"; }
  | D     { $katakana = "ジョ"; }
  | DH AA { $katakana = "ジャ"; }
  | DH AE { $katakana = "デェ"; }
  | DH AH { $katakana = "ジャー"; }
  | DH AO { $katakana = "ジャオ"; }
  | DH AW { $katakana = "ジャウ"; }
  | DH AY { $katakana = "ジャイ"; }
  | DH IH { $katakana = "ディー"; }
  | DH IY { $katakana = "ディ"; }
  | DH UH { $katakana = "ジュ"; }
  | DH UW { $katakana = "ジュ"; }
  | DH EH { $katakana = "デェ"; }
  | DH ER { $katakana = "ジャー"; }
  | DH EY { $katakana = "デェイ"; }
  | DH OW { $katakana = "ジョウ"; }
  | DH OY { $katakana = "ジョイ"; }
  | DH    { $katakana = "ジョ"; }
  | F  AA { $katakana = "ファ"; }
  | F  AE { $katakana = "フェ"; }
  | F  AH { $katakana = "ファー"; }
  | F  AO { $katakana = "ファオ"; }
  | F  AW { $katakana = "ファウ"; }
  | F  AY { $katakana = "ファイ"; }
  | F  IH { $katakana = "フィー"; }
  | F  IY { $katakana = "フィ"; }
  | F  UH { $katakana = "フ"; }
  | F  UW { $katakana = "フ"; }
  | F  EH { $katakana = "フェ"; }
  | F  ER { $katakana = "ファー"; }
  | F  EY { $katakana = "フェイ"; }
  | F  OW { $katakana = "フォウ"; }
  | F  OY { $katakana = "フォイ"; }
  | F     { $katakana = "フ"; }
  | G  AA { $katakana = "ガ"; }
  | G  AE { $katakana = "ゲ"; }
  | G  AH { $katakana = "ガー"; }
  | G  AO { $katakana = "ガオ"; }
  | G  AW { $katakana = "ガウ"; }
  | G  AY { $katakana = "ガイ"; }
  | G  IH { $katakana = "ギー"; }
  | G  IY { $katakana = "ギ"; }
  | G  UH { $katakana = "グ"; }
  | G  UW { $katakana = "グ"; }
  | G  EH { $katakana = "ゲ"; }
  | G  ER { $katakana = "ガー"; }
  | G  EY { $katakana = "ゲイ"; }
  | G  OW { $katakana = "ゴウ"; }
  | G  OY { $katakana = "ゴイ"; }
  | G     { $katakana = "グ"; }
  | HH AA { $katakana = "ハ"; }
  | HH AE { $katakana = "ヘ"; }
  | HH AH { $katakana = "ハー"; }
  | HH AO { $katakana = "ハオ"; }
  | HH AW { $katakana = "ハウ"; }
  | HH AY { $katakana = "ハイ"; }
  | HH IH { $katakana = "ヒー"; }
  | HH IY { $katakana = "ヒ"; }
  | HH UH { $katakana = "フ"; }
  | HH UW { $katakana = "フ"; }
  | HH EH { $katakana = "ヘ"; }
  | HH ER { $katakana = "ハー"; }
  | HH EY { $katakana = "ヘイ"; }
  | HH OW { $katakana = "ホウ"; }
  | HH OY { $katakana = "ホイ"; }
  | HH    { $katakana = "フ"; }
  | JH AA { $katakana = "ジャ"; }
  | JH AE { $katakana = "ジェ"; }
  | JH AH { $katakana = "ジャー"; }
  | JH AO { $katakana = "ジャオ"; }
  | JH AW { $katakana = "ジャウ"; }
  | JH AY { $katakana = "ジャイ"; }
  | JH IH { $katakana = "ヂー"; }
  | JH IY { $katakana = "ヂ"; }
  | JH UH { $katakana = "ジュ"; }
  | JH UW { $katakana = "ジュ"; }
  | JH EH { $katakana = "ジェ"; }
  | JH ER { $katakana = "ジャー"; }
  | JH EY { $katakana = "ジェイ"; }
  | JH OW { $katakana = "ジョウ"; }
  | JH OY { $katakana = "ジョイ"; }
  | JH    { $katakana = "ヂ"; }
  | K  AA { $katakana = "カ"; }
  | K  AE { $katakana = "ケ"; }
  | K  AH { $katakana = "カー"; }
  | K  AO { $katakana = "カオ"; }
  | K  AW { $katakana = "カウ"; }
  | K  AY { $katakana = "カイ"; }
  | K  IH { $katakana = "キー"; }
  | K  IY { $katakana = "キ"; }
  | K  UH { $katakana = "ク"; }
  | K  UW { $katakana = "ク"; }
  | K  EH { $katakana = "ケ"; }
  | K  ER { $katakana = "カー"; }
  | K  EY { $katakana = "ケイ"; }
  | K  OW { $katakana = "コウ"; }
  | K  OY { $katakana = "コイ"; }
  | K     { $katakana = "ク"; }
  | L  AA { $katakana = "ラ"; }
  | L  AE { $katakana = "レ"; }
  | L  AH { $katakana = "ラー"; }
  | L  AO { $katakana = "ラオ"; }
  | L  AW { $katakana = "ラウ"; }
  | L  AY { $katakana = "ライ"; }
  | L  IH { $katakana = "リー"; }
  | L  IY { $katakana = "リ"; }
  | L  UH { $katakana = "ル"; }
  | L  UW { $katakana = "ル"; }
  | L  EH { $katakana = "レ"; }
  | L  ER { $katakana = "ラー"; }
  | L  EY { $katakana = "レイ"; }
  | L  OW { $katakana = "ロウ"; }
  | L  OY { $katakana = "ロイ"; }
  | L     { $katakana = "ル"; }
  | M  AA { $katakana = "マ"; }
  | M  AE { $katakana = "メ"; }
  | M  AH { $katakana = "マー"; }
  | M  AO { $katakana = "マオ"; }
  | M  AW { $katakana = "マウ"; }
  | M  AY { $katakana = "マイ"; }
  | M  IH { $katakana = "ミー"; }
  | M  IY { $katakana = "ミ"; }
  | M  UH { $katakana = "ム"; }
  | M  UW { $katakana = "ム"; }
  | M  EH { $katakana = "メ"; }
  | M  ER { $katakana = "マー"; }
  | M  EY { $katakana = "メイ"; }
  | M  OW { $katakana = "モウ"; }
  | M  OY { $katakana = "モイ"; }
  | M     { $katakana = "ム"; }
  | N  AA { $katakana = "ナ"; }
  | N  AE { $katakana = "ネ"; }
  | N  AH { $katakana = "ナー"; }
  | N  AO { $katakana = "ナオ"; }
  | N  AW { $katakana = "ナウ"; }
  | N  AY { $katakana = "ナイ"; }
  | N  IH { $katakana = "ニー"; }
  | N  IY { $katakana = "ニ"; }
  | N  UH { $katakana = "ヌ"; }
  | N  UW { $katakana = "ヌ"; }
  | N  EH { $katakana = "ネ"; }
  | N  ER { $katakana = "ナー"; }
  | N  EY { $katakana = "ネイ"; }
  | N  OW { $katakana = "ノウ"; }
  | N  OY { $katakana = "ノイ"; }
  | N     { $katakana = "ン"; }
  | NG AA { $katakana = "ナ"; }
  | NG AE { $katakana = "ネ"; }
  | NG AH { $katakana = "ナー"; }
  | NG AO { $katakana = "ナオ"; }
  | NG AW { $katakana = "ナウ"; }
  | NG AY { $katakana = "ナイ"; }
  | NG IH { $katakana = "ニー"; }
  | NG IY { $katakana = "ニ"; }
  | NG UH { $katakana = "ヌ"; }
  | NG UW { $katakana = "ヌ"; }
  | NG EH { $katakana = "ネ"; }
  | NG ER { $katakana = "ナー"; }
  | NG EY { $katakana = "ネイ"; }
  | NG OW { $katakana = "ノウ"; }
  | NG OY { $katakana = "ノイ"; }
  | NG    { $katakana = "ン"; }
  | P  AA { $katakana = "パ"; }
  | P  AE { $katakana = "ペ"; }
  | P  AH { $katakana = "パー"; }
  | P  AO { $katakana = "パオ"; }
  | P  AW { $katakana = "パウ"; }
  | P  AY { $katakana = "パイ"; }
  | P  IH { $katakana = "ピー"; }
  | P  IY { $katakana = "ピ"; }
  | P  UH { $katakana = "プ"; }
  | P  UW { $katakana = "プ"; }
  | P  EH { $katakana = "ペ"; }
  | P  ER { $katakana = "パー"; }
  | P  EY { $katakana = "ペイ"; }
  | P  OW { $katakana = "ポウ"; }
  | P  OY { $katakana = "ポイ"; }
  | P     { $katakana = "プ"; }
  | R  AA { $katakana = "ラ"; }
  | R  AE { $katakana = "レ"; }
  | R  AH { $katakana = "ラー"; }
  | R  AO { $katakana = "ラオ"; }
  | R  AW { $katakana = "ラウ"; }
  | R  AY { $katakana = "ライ"; }
  | R  IH { $katakana = "リー"; }
  | R  IY { $katakana = "リ"; }
  | R  UH { $katakana = "ル"; }
  | R  UW { $katakana = "ル"; }
  | R  EH { $katakana = "レ"; }
  | R  ER { $katakana = "ラー"; }
  | R  EY { $katakana = "レイ"; }
  | R  OW { $katakana = "ロウ"; }
  | R  OY { $katakana = "ロイ"; }
  | R     { $katakana = "ル"; }
  | S  AA { $katakana = "サ"; }
  | S  AE { $katakana = "セ"; }
  | S  AH { $katakana = "サー"; }
  | S  AO { $katakana = "サオ"; }
  | S  AW { $katakana = "サウ"; }
  | S  AY { $katakana = "サイ"; }
  | S  IH { $katakana = "シー"; }
  | S  IY { $katakana = "シ"; }
  | S  UH { $katakana = "ス"; }
  | S  UW { $katakana = "ス"; }
  | S  EH { $katakana = "セ"; }
  | S  ER { $katakana = "サー"; }
  | S  EY { $katakana = "セイ"; }
  | S  OW { $katakana = "ソウ"; }
  | S  OY { $katakana = "ソイ"; }
  | S     { $katakana = "ス"; }
  | SH AA { $katakana = "シャ"; }
  | SH AE { $katakana = "シェ"; }
  | SH AH { $katakana = "シャー"; }
  | SH AO { $katakana = "シャオ"; }
  | SH AW { $katakana = "シャウ"; }
  | SH AY { $katakana = "シャイ"; }
  | SH IH { $katakana = "シー"; }
  | SH IY { $katakana = "シ"; }
  | SH UH { $katakana = "シュ"; }
  | SH UW { $katakana = "シュ"; }
  | SH EH { $katakana = "シェ"; }
  | SH ER { $katakana = "シャー"; }
  | SH EY { $katakana = "シェイ"; }
  | SH OW { $katakana = "ショウ"; }
  | SH OY { $katakana = "ショイ"; }
  | SH    { $katakana = "シュ"; }
  | T  AA { $katakana = "タ"; }
  | T  AE { $katakana = "テ"; }
  | T  AH { $katakana = "ター"; }
  | T  AO { $katakana = "タオ"; }
  | T  AW { $katakana = "タウ"; }
  | T  AY { $katakana = "タイ"; }
  | T  IH { $katakana = "ティー"; }
  | T  IY { $katakana = "ティ"; }
  | T  UH { $katakana = "ツ"; }
  | T  UW { $katakana = "ツ"; }
  | T  EH { $katakana = "テ"; }
  | T  ER { $katakana = "ター"; }
  | T  EY { $katakana = "テイ"; }
  | T  OW { $katakana = "トウ"; }
  | T  OY { $katakana = "トイ"; }
  | T     { $katakana = "ト"; }
  | TH AA { $katakana = "サ"; }
  | TH AE { $katakana = "セ"; }
  | TH AH { $katakana = "サー"; }
  | TH AO { $katakana = "サオ"; }
  | TH AW { $katakana = "サウ"; }
  | TH AY { $katakana = "サイ"; }
  | TH IH { $katakana = "シー"; }
  | TH IY { $katakana = "シ"; }
  | TH UH { $katakana = "ス"; }
  | TH UW { $katakana = "ス"; }
  | TH EH { $katakana = "セ"; }
  | TH ER { $katakana = "サー"; }
  | TH EY { $katakana = "セイ"; }
  | TH OW { $katakana = "ソウ"; }
  | TH OY { $katakana = "ソイ"; }
  | TH    { $katakana = "ス"; }
  | V  AA { $katakana = "バ"; }
  | V  AE { $katakana = "ベ"; }
  | V  AH { $katakana = "バー"; }
  | V  AO { $katakana = "バオ"; }
  | V  AW { $katakana = "バウ"; }
  | V  AY { $katakana = "バイ"; }
  | V  IH { $katakana = "ビー"; }
  | V  IY { $katakana = "ビ"; }
  | V  UH { $katakana = "ブ"; }
  | V  UW { $katakana = "ブ"; }
  | V  EH { $katakana = "ベ"; }
  | V  ER { $katakana = "バー"; }
  | V  EY { $katakana = "ベイ"; }
  | V  OW { $katakana = "ボウ"; }
  | V  OY { $katakana = "ボイ"; }
  | V     { $katakana = "ブ"; }
  | W  AA { $katakana = "ワ"; }
  | W  AE { $katakana = "ウェ"; }
  | W  AH { $katakana = "ワー"; }
  | W  AO { $katakana = "ワオ"; }
  | W  AW { $katakana = "ワウ"; }
  | W  AY { $katakana = "ワイ"; }
  | W  IH { $katakana = "ウィー"; }
  | W  IY { $katakana = "ウィ"; }
  | W  UH { $katakana = "ウ"; }
  | W  UW { $katakana = "ウ"; }
  | W  EH { $katakana = "ウェ"; }
  | W  ER { $katakana = "ワー"; }
  | W  EY { $katakana = "ウェイ"; }
  | W  OW { $katakana = "ウォウ"; }
  | W  OY { $katakana = "ウォイ"; }
  | W     { $katakana = "ウ"; }
  | Y  AA { $katakana = "ヤ"; }
  | Y  AE { $katakana = "イエ"; }
  | Y  AH { $katakana = "ヤー"; }
  | Y  AO { $katakana = "ヤオ"; }
  | Y  AW { $katakana = "ヤウ"; }
  | Y  AY { $katakana = "ヤイ"; }
  | Y  IH { $katakana = "イー"; }
  | Y  IY { $katakana = "イ"; }
  | Y  UH { $katakana = "ユ"; }
  | Y  UW { $katakana = "ユ"; }
  | Y  EH { $katakana = "イエ"; }
  | Y  ER { $katakana = "ヤー"; }
  | Y  EY { $katakana = "イエイ"; }
  | Y  OW { $katakana = "ヨウ"; }
  | Y  OY { $katakana = "ヨイ"; }
  | Y     { $katakana = "イ"; }
  | Z  AA { $katakana = "ザ"; }
  | Z  AE { $katakana = "ゼ"; }
  | Z  AH { $katakana = "ザー"; }
  | Z  AO { $katakana = "ザオ"; }
  | Z  AW { $katakana = "ザウ"; }
  | Z  AY { $katakana = "ザイ"; }
  | Z  IH { $katakana = "ジィー"; }
  | Z  IY { $katakana = "ジィ"; }
  | Z  UH { $katakana = "ズ"; }
  | Z  UW { $katakana = "ズ"; }
  | Z  EH { $katakana = "ゼ"; }
  | Z  ER { $katakana = "ザー"; }
  | Z  EY { $katakana = "ゼイ"; }
  | Z  OW { $katakana = "ゾウ"; }
  | Z  OY { $katakana = "ゾイ"; }
  | Z     { $katakana = "ズ"; }
  | ZH AA { $katakana = "ジャ"; }
  | ZH AE { $katakana = "ジェ"; }
  | ZH AH { $katakana = "ジャー"; }
  | ZH AO { $katakana = "ジャオ"; }
  | ZH AW { $katakana = "ジャウ"; }
  | ZH AY { $katakana = "ジャイ"; }
  | ZH IH { $katakana = "ゼィー"; }
  | ZH IY { $katakana = "ゼィ"; }
  | ZH UH { $katakana = "ジュ"; }
  | ZH UW { $katakana = "ジュ"; }
  | ZH EH { $katakana = "ジェ"; }
  | ZH ER { $katakana = "ジャー"; }
  | ZH EY { $katakana = "ジェイ"; }
  | ZH OW { $katakana = "ジョウ"; }
  | ZH OY { $katakana = "ジョイ"; }
  | ZH    { $katakana = "ジュ"; }
  | AA    { $katakana = "ア"; }
  | AE    { $katakana = "エ"; }
  | AH    { $katakana = "アー"; }
  | AO    { $katakana = "アオ"; }
  | AW    { $katakana = "アウ"; }
  | AY    { $katakana = "アイ"; }
  | IH    { $katakana = "イー"; }
  | IY    { $katakana = "イ"; }
  | UH    { $katakana = "ウ"; }
  | UW    { $katakana = "ウ"; }
  | EH    { $katakana = "エ"; }
  | ER    { $katakana = "アー"; }
  | EY    { $katakana = "エイ"; }
  | OW    { $katakana = "オウ"; }
  | OY    { $katakana = "オイ"; }
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

