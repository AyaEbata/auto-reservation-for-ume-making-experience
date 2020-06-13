# auto-reservation-for-ume-making-experience
某梅酒メーカーの梅体験専門店の梅体験を自動予約するツール。

## 開発環境
- Java 14.0.2
- Gradle 6.5
- JUnit 5.6.2
- Selenide 5.12.2

## 仕様
某梅酒メーカーの梅体験専門店の梅体験をSelenideを使って自動で予約する。  
（夜中の0時ちょうどに2週間後の予約が開始するのだけれど、オープンしたばっかだしコロナで席数少ないから競争率高いの。。。）

現状では以下に仕様が限られている。
- 鎌倉店での予約
- リクルートIDを使っての予約

## 実行方法
1. リクルートIDを作成して、名前とフリガナと電話番号を事前に登録しておく。
2. `config_sample.properties`をコピーして`config.properties`を作成し、内容を記入する。
3. AutoReservationSpecクラスのmainメソッドを時間になったら実行。（ここも自動にしたい…）

※ 上記の3番実行後は、入力内容をちゃんと目視で確認して、自分で「上記に同意して予約を確定する」ボタンを押す。

## 開発メモ
- Java 14を使う場合はGradle 6.3以降を使う
  - `gradle/wrapper/gradle-wrapper.properties`にてGradleのバージョン変更が出来る
