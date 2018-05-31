#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_SOME_key -iv $encrypted_SOME_iv -in build/signingkey.asc.enc -out build/signingkey.asc -d
    gpg --fast-import build/signingkey.asc
fi