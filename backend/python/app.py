from flask import Flask
from flask import request
from my_clustering import encode, clustering
# from urllib.parse import quote # url 한글 인코딩 Error 해결

app = Flask(__name__)

@app.route('/cluster')
def cluster():
    groupId = request.args.get('groupId', 'groupId')
    print(groupId)
    return clustering(groupId)

if __name__ == '__main__':
    app.run()
