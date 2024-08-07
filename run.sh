docker compose down
docker compose up --wait
sleep 5

PGPASSWORD=mypassword psql -h localhost -p 5433 -f query.sql mydb myuser

docker compose down
