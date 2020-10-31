# FirstAct

## showFirstActs

- 取得できる Act の件数には上限と下限がある
- ActId を指定すると、それより以前の Act を取得できる(pagenation : cursor)
- もし同じ作者の続きがあれば、それも特別に１つだけ取得(上と被っても良い)

## writeFirstAct

- section
  - 横書き
  - 字数制限1~400文字
  - 文字サイズと一文の文字数は読む媒体に依って変更(リフロー)
  - 禁則処理を行う
- title
  - 横書き
  - 字数制限1~20文字
- dice
  - 振るのは9個
  - 最初から振られた状態で取得
  - 選ぶのは3個
- act
  - 以下が守られていなければpostActできない
    - section の字数制限
    - title の字数制限
