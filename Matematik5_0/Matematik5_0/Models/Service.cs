using Matematik5_0.Models.Forum;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Matematik5_0.Models
{
    public class Service
    {
        private readonly HttpClient _httpClient;

        public Service(HttpClient client)
        {
            _httpClient = client;
        }

        // Categories
        public async Task<List<Category>> GetCategoriesAsync ( )
        {
            var uri = new Uri($"https://localhost:44356/category");
            var responseString = await _httpClient.GetStringAsync(uri);
            List<Category> items = JsonConvert.DeserializeObject<List<Category>>(responseString);
            return items;
        }

        public async Task<Category> GetCategoryFromIdAsync ( int? id )
        {
            var uri = new Uri($"https://localhost:44356/category/{id}");
            var responseString = await _httpClient.GetStringAsync(uri);
            var item = JsonConvert.DeserializeObject<Category>(responseString);
            return item;
        }

        public async Task DeleteCategoryFromIdAsync ( int? id )
        {
            var uri = new Uri($"https://localhost:44356/category/{id}");
            await _httpClient.DeleteAsync(uri);
        }

        public async Task PostCategoryAsync ( Category category )
        {
            var uri = new Uri("https://localhost:44356/category");
            var content = JsonConvert.SerializeObject(category);
            var httpContent = new StringContent(content, Encoding.UTF8, "application/json");
            await _httpClient.PostAsync(uri, httpContent);
        }

        public async Task PutCategoryAsync ( int? id, Category category )
        {
            var uri = new Uri($"https://localhost:44356/category/{id}");
            var content = JsonConvert.SerializeObject(category);
            var httpContent = new StringContent(content, Encoding.UTF8, "application/json");
            await _httpClient.PutAsync(uri, httpContent);
        }

        //Posts
        public async Task PostPostAsync ( Post post )
        {
            var uri = new Uri("https://localhost:44356/post");
            var content = JsonConvert.SerializeObject(post);
            var httpContent = new StringContent(content, Encoding.UTF8, "application/json");
            await _httpClient.PostAsync(uri, httpContent);
        }

        public async Task<Post> GetPostFromIdAsync ( int? id )
        {
            var uri = new Uri($"https://localhost:44356/post/{id}");
            var responseString = await _httpClient.GetStringAsync(uri);
            var item = JsonConvert.DeserializeObject<Post>(responseString);
            return item;
        }

        //Comments
        public async Task PostCommentAsync ( Comment comment )
        {
            var uri = new Uri("https://localhost:44356/comment");
            var content = JsonConvert.SerializeObject(comment);
            var httpContent = new StringContent(content, Encoding.UTF8, "application/json");
            await _httpClient.PostAsync(uri, httpContent);
        }

    }
}
