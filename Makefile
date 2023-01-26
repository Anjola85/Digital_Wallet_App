dev:
	docker compose -f docker-compose.yml up -d --build --remove-orphans
stop:
	docker compose -f docker-compose.yml down
