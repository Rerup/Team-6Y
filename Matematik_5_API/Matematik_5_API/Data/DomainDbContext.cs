using Matematik_5_API.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Matematik_5_API.Data
{
    public class DomainDbContext : DbContext
    {
        public DbSet<Category> Categories { get; set; }
        public DbSet<Post> Posts { get; set; }
        public DbSet<Comment> Comments { get; set; }
        public DomainDbContext ( DbContextOptions<DomainDbContext> options )
            : base(options)
        {
            Database.EnsureCreated();
        }
    }
}
