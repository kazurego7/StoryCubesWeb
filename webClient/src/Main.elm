module Main exposing (Model, Msg, init, subscriptions, update, view)

import Browser
import Html exposing (..)
import Html.Events exposing (..)
import Random exposing (..)
import StoryCube.StoryCube as StoryCube
import StoryCube.Symbol.Picture as Picture


main : Program () Model Msg
main =
    Browser.element
        { init = init
        , view = view
        , update = update
        , subscriptions = subscriptions
        }


type alias Model =
    { sideOfCubes : Int
    }


init : flags -> ( Model, Cmd Msg )
init _ =
    ( Model 0, Cmd.none )


type Msg
    = Roll
    | NewSide Int


update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        Roll ->
            ( model, Random.generate NewSide (Random.int 1 6) )

        NewSide side ->
            ( { model | sideOfCubes = side }, Cmd.none )


subscriptions : Model -> Sub Msg
subscriptions model =
    Sub.none


view : Model -> Html Msg
view model =
    button [ onClick Roll ]
        [ text (String.fromInt model.sideOfCubes) ]
