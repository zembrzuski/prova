FROM python:3.6-alpine3.6

COPY . /app
WORKDIR /app

# TODO rodar testes aqui com tox e pan.

RUN pip install -r requirements.txt

CMD python main.py