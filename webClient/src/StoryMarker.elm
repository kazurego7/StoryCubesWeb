module StoryMarker exposing (Finished, StoryMarker(..), Unfinished, finish, storyMarker, unuse, use)

import StoryCube.StoryCube exposing (..)


type alias Finished =
    List StoryCube


type alias Used =
    List StoryCube


type alias Unfinished =
    List StoryCube


{-| ストーリーのしおり。物語を作り終わったキューブと、まだ作っていないキューブをわける役割。
-}
type StoryMarker
    = StoryMarker Finished Used Unfinished


{-| storyMarker のコンストラクタ
-}
storyMarker : List StoryCube -> StoryMarker
storyMarker storyCubes =
    StoryMarker [] [] storyCubes


{-| 物語に使うキューブを選択済みにする
-}
use : StoryCube -> StoryMarker -> Maybe StoryMarker
use cube (StoryMarker finished used unfinished) =
    if List.any (equal cube) unfinished then
        Just
            (StoryMarker
                finished
                (cube :: used)
                (List.filter (equal cube >> not) unfinished)
            )

    else
        Nothing


{-| 物語に使うキューブを未選択にする
-}
unuse : StoryCube -> StoryMarker -> Maybe StoryMarker
unuse cube (StoryMarker finished used unfinished) =
    if List.any (equal cube) used then
        Just
            (StoryMarker
                finished
                (List.filter (equal cube >> not) used)
                (cube :: unfinished)
            )

    else
        Nothing


{-| 物語に使ったキューブを使用済みにする
-}
finish : StoryMarker -> StoryMarker
finish (StoryMarker finished used unfinished) =
    StoryMarker
        (List.append finished used)
        []
        unfinished
