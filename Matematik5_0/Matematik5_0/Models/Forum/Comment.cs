using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Matematik5_0.Models.Forum
{
    public class Comment
    {
        [Key]
        public int ID { get; set; }
        [MaxLength(350, ErrorMessage = "To long")]
        public string Content { get; set; }
        public DateTime CreatedWhen { get; set; }
        public string Author { get; set; }
        public int UserID { get; set; }
        public int PostID { get; set; }

        //Navigation Property
        public Post Post { get; set; }
    }
}
