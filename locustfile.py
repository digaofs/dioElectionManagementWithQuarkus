import random, requests, socket

from locust import HttpUser, task, between


class VoteDIO(HttpUser):
    wait_time = between(1, 5)

    @task
    def voting(self):
        proxies = {
            'http': 'http://localhost:80',  # Traefik está escutando na porta 80 para HTTP
            'https': 'http://localhost:80'  # Se o Traefik estiver configurado para HTTPS, ajuste conforme necessário
        }
        session = requests.Session()
        session.proxies.update(proxies)

        for election in self.client.get("/api/voting").json():
            election_id = election['id']
            candidate_id = random.choice(election['candidates'])

            self.client.post(f"/api/voting/elections/{election_id}/candidates/{candidate_id}")

# locust --headless --users 1 --spawn-rate 1 -H http://vote.dio.localhost