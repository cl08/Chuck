from flask import Flask
from flask import request
from my_clustering import encode, clustering
# from urllib.parse import quote # url 한글 인코딩 Error 해결

app = Flask(__name__)

@app.route('/cluster')
def cluster():
    groupId = request.args.get('groupId', 'groupId')
    imagePath = request.args.get('imagePath', 'imagePath')
    print(groupId)
    print(imagePath)
    return encode(groupId, [imagePath])

if __name__ == '__main__':
    app.run()
