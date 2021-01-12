using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Matematik_5_API.Models
{
    public class Post
    {
        public int ID { get; set; }
        public string Title { get; set; }
        public string Content { get; set; }
        public DateTime CreatedWhen { get; set; }
        public int Author { get; set; }
        public int CategoryID { get; set; }
        [Timestamp]
        public byte[] RowVersion { get; set; }

        //Navigation Property
        public Category Category { get; set; }
        public IEnumerable<Comment> Comments { get; set; }
    }
}
