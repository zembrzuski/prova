import logging
import os

import tornado.ioloop
import web_app as web_app
from utils.yaml_loader import load_config
from elasticsearch import Elasticsearch


def generate_some_data():
    user1 = {
        'cpf': '11111111111',
        'datetime': '2015/01/01 12:10:30',
        'movements': [
            {
                'description': 'supermercado',
                'value': 35.20,
                'mode': 'credit-card',
                'datetime': '2018/01/01 12:10:30'
            },
            {
                'description': 'venda da esquina',
                'value': 350.20,
                'mode': 'dinheiro',
                'datetime': '2017/01/01 12:10:30'
            },
            {
                'description': 'cachorro-quente',
                'value': 5.20,
                'mode': 'credit-card',
                'datetime': '2015/01/01 12:10:30'
            }
        ],
        'last_movement': {
            'description': 'supermercado',
            'value': 35.20,
            'mode': 'credit-card',
            'datetime': '2018/01/01 12:10:30'
        }
    }

    user2 = {
        'cpf': '22222222222',
        'datetime': '2018/03/05 12:15:30',
        'movements': [
            {
                'description': 'fruteira',
                'value': 22.34,
                'mode': 'credit-card',
                'datetime': '2018/01/01 12:10:30'
            },
            {
                'description': 'gasolina',
                'value': 120.20,
                'mode': 'credit-card',
                'datetime': '2017/01/01 12:10:30'
            }
        ],
        'last_movement': {
            'description': 'fruteira',
            'value': 22.34,
            'mode': 'credit-card',
            'datetime': '2018/01/01 12:10:30'
        }
    }

    user3 = {
        'cpf': '33333333333',
        'datetime': '2018/03/01 23:15:30',
        'movements': [
            {
                'description': 'padaria',
                'value': 11.23,
                'mode': 'money',
                'datetime': '2012/01/01 12:10:30'
            },
            {
                'description': 'guitarra',
                'value': 120.20,
                'mode': 'credit-card',
                'datetime': '2010/01/01 12:10:30'
            }
        ],
        'last_movement': {
            'description': 'guitarra',
            'value': 120.20,
            'mode': 'credit-card',
            'datetime': '2010/01/01 12:10:30'
        }
    }

    user4 = {
        'cpf': '44444444444',
        'datetime': '2019/03/01 23:15:30',
        'movements': []
    }

    user5 = {
        'cpf': '55555555555',
        'datetime': '2018/03/01 23:15:30',
        'movements': [
            {
                'description': 'cafe',
                'value': 50.23,
                'mode': 'money',
                'datetime': '2012/01/01 12:10:30'
            },
            {
                'description': 'pao',
                'value': 120.20,
                'mode': 'credit-card',
                'datetime': '2010/01/01 12:10:30'
            },
            {
                'description': 'cigarros',
                'value': 5.20,
                'mode': 'credit-card',
                'datetime': '2010/01/01 12:09:25'
            },
            {
                'description': 'remedio',
                'value': 2.50,
                'mode': 'credit-card',
                'datetime': '2010/01/01 10:08:30'
            },
            {
                'description': 'verduras',
                'value': 12.50,
                'mode': 'money',
                'datetime': '2010/01/01 10:00:30'
            }
        ],
        'last_movement': {
            'description': 'pao',
            'value': 120.20,
            'mode': 'credit-card',
            'datetime': '2010/01/01 12:10:30'
        }
    }

    return [user1, user2, user3, user4, user5]


if __name__ == '__main__':
    config = load_config(os.getenv('config_path', 'config/local.yml'))

    logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.INFO)
    logging.info(config)
    logging.info(config['elasticsearch']['hosts'])

    elasticsearch = Elasticsearch(config['elasticsearch']['hosts'])

    generated_data = generate_some_data()
    [elasticsearch.index(index="users", doc_type='user', id=x['cpf'], body=x) for x in generated_data]

    app = web_app.create_web_app(elasticsearch)

    logging.info('Backend service listening on 8888')
    app.listen(8888)
    tornado.ioloop.IOLoop.current().start()
