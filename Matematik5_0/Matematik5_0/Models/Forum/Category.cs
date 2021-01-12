using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Matematik5_0.Models.Forum
{
    public class Category
    {
        [Key]
        public int ID { get; set; }
        [Required]
        [MaxLength(15, ErrorMessage = "To long")]
        public string Title { get; set; }
        [Required]
        [MaxLength(350, ErrorMessage = "To long")]
        public string Description { get; set; }
        [Required]
        public DateTime CreatedWhen { get; set; }

        [Timestamp]
        public byte[] RowVersion { get; set; }

        // Navigation Properties
        public IEnumerable<Post> Posts { get; set; }
    }
}
