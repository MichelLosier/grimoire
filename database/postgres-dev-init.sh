#!/usr/bin/env bash

POSTGRES_DB=grimoire
POSTGRES_PASSWORD=secret

docker run --rm --name pg-docker-grimoire \
 -e POSTGRES_DB=$POSTGRES_DB \
 -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
 -v $HOME/postgres-volumes:/var/lib/postgresql/data \
 -p 5432:5432 \
 -d postgres

