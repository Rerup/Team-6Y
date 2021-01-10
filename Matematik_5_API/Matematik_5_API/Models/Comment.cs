using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Matematik_5_API.Models
{
    public class Comment
    {
        public int ID { get; set; }
        public string Content { get; set; }
        public DateTime CreatedWhen { get; set; }
        public string Author { get; set; }
        public int UserID { get; set; }
        public int PostID { get; set; }

        //Navigation Property
        public Post Post { get; set; }
    }
}
