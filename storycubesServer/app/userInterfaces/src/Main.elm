module Main exposing (..)

-- Press buttons to increment and decrement a counter.
--
-- Read how it works:
--   https://guide.elm-lang.org/architecture/buttons.html
--

import Array exposing (Array)
import Browser
import Html exposing (..)
import Html.Attributes exposing (class, style)
import Html.Events exposing (..)
import Platform.Cmd exposing (none)



-- MAIN


main : Program () Model Msg
main =
    Browser.sandbox { init = init, update = update, view = view }



-- MODEL


type alias Model =
    {}


init : Model
init =
    {}



-- UPDATE


type Msg
    = None


update : Msg -> Model -> Model
update msg model =
    case msg of
        None ->
            {}



-- VIEW


story : SectionText -> Html msg
story (SectionText sectionText) =
    let
        actSentence : String -> Html msg
        actSentence sentenceText =
            p [ class "is-family-monospace is-size-6 my-2", style "line-height" "2em", style "text-indent" "1em" ] [ text sentenceText ]

        actSection sentenceTexts =
            div [ class "card my-2" ]
                [ section [ class "card-content" ]
                    [ div [ class "level is-mobile" ] [ div [ class "level-left" ] [], button [ class "delete level-right" ] [] ]
                    , div [ class "content" ] (List.map actSentence sentenceTexts)
                    ]
                ]
    in
    div [ class "columns is-mobile is-centered is-gapless" ]
        [ div [ class "column", style "max-width" "35em" ]
            [ actSection sectionText.firstAct
            , actSection sectionText.middleAct
            , actSection sectionText.lastAct
            ]
        ]


type SectionText
    = SectionText
        { title : String
        , firstAct : List String
        , middleAct : List String
        , lastAct : List String
        }


texts1 =
    SectionText
        { title = "吾輩は猫である"
        , firstAct =
            [ "吾輩は猫である。名前はまだ無い。"
            , "どこで生れたかとんと見当がつかぬ。何でも薄暗いじめじめした所でニャーニャー泣いていた事だけは記憶している。吾輩はここで始めて人間というものを見た。しかもあとで聞くとそれは書生という人間中で一番獰悪な種族であったそうだ。"
            , "この書生というのは時々我々を捕つかまえて煮て食うという話である。しかしその当時は何という考もなかったから別段恐しいとも思わなかった。ただ彼の掌に載せられてスーと持ち上げられた時何だかフワフワした感じがあったばかりである。掌の上で少し落ちついて書生の顔を見たのがいわゆる人間というものの見始めであろう。この時妙なものだと思った感じが今でも残っている。第一毛をもって装飾されべきはずの顔がつるつるしてまるで薬缶だ。その後猫にもだいぶ逢ったがこんな片輪には一度も出会わした事がない。のみならず顔の真中があまりに突起している。そうしてその穴の中から時々ぷうぷうと煙を吹く。どうも咽せぽくて実に弱った。これが人間の飲む煙草というものである事はようやくこの頃知った。"
            ]
        , middleAct =
            [ "この書生の掌の裏でしばらくはよい心持に坐っておったが、しばらくすると非常な速力で運転し始めた。書生が動くのか自分だけが動くのか分らないが無暗に眼が廻る。胸が悪くなる。到底助からないと思っていると、どさりと音がして眼から火が出た。それまでは記憶しているがあとは何の事やらいくら考え出そうとしても分らない。"
            , "ふと気が付いて見ると書生はいない。たくさんおった兄弟が一疋も見えぬ。肝心の母親さえ姿を隠してしまった。その上今までの所とは違って無暗に明るい。眼を明いていられぬくらいだ。はてな何でも容子がおかしいと、のそのそ這い出して見ると非常に痛い。吾輩は藁の上から急に笹原の中へ棄てられたのである。"
            ]
        , lastAct =
            [ "ようやくの思いで笹原を這い出すと向うに大きな池がある。吾輩は池の前に坐ってどうしたらよかろうと考えて見た。別にこれという分別も出ない。しばらくして泣いたら書生がまた迎に来てくれるかと考え付いた。ニャー、ニャーと試みにやって見たが誰も来ない。そのうち池の上をさらさらと風が渡って日が暮れかかる。腹が非常に減って来た。泣きたくても声が出ない。仕方がない、何でもよいから食物のある所まであるこうと決心をしてそろりそろりと池を左りに廻り始めた。どうも非常に苦しい。そこを我慢して無理やりに這って行くとようやくの事で何となく人間臭い所へ出た。ここへ這入ったら、どうにかなると思って竹垣の崩れた穴から、とある邸内にもぐり込んだ。縁は不思議なもので、もしこの竹垣が破れていなかったなら、吾輩はついに路傍に餓死したかも知れんのである。一樹の蔭とはよく云ったものだ。"
            ]
        }


texts2 =
    SectionText
        { title = "ふくらはぎ"
        , firstAct =
            [ "俺がおととい死んだので"
            , "友だちが黒い服を着こんで集まってきた"
            , "驚いたことにおいおい泣いているあいつは"
            , "生前俺が電話にも出なかった男"
            , "まっ白なベンツに乗ってやってきた"
            ]
        , middleAct =
            [ "俺はおとつい死んだのに"
            , "世界は滅びる気配もない"
            , "坊主の袈裟はきらきらと冬の陽に輝いて"
            , "隣家の小五は俺のパソコンをいたずらしてる"
            , "おや線香ってこんなにいい匂いだったのか"
            ]
        , lastAct =
            [ "俺はおとつい死んだから"
            , "もう今日に何の意味もない"
            , "おかげで意味じゃないものがよく分る"
            , "もっとしつこく触っておけばよかったなあ"
            , "あのひとのふくらはぎに"
            ]
        }


view : Model -> Html Msg
view model =
    story texts1
