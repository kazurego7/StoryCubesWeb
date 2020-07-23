module Story exposing (Act(..), Prologue(..), PrologueList(..))

import Sentence exposing (..)
import StoryCube.Symbol.Symbol exposing (..)
import StoryMarker exposing (..)


type Prologue
    = Prologue { marker : List Symbol, nextActs : List Act }


type PrologueList
    = PrologueList (List Prologue)


type Act
    = Act
        { marker : List Symbol
        , sentence : Sentence
        , nextActs : List Act
        }
