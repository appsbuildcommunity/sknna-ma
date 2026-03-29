FROM node:20

WORKDIR /app

# copy package files
COPY package*.json ./

# install dependencies
RUN npm ci

# copy project
COPY . .

EXPOSE 5173

CMD ["npm", "run", "dev", "--", "--host"]